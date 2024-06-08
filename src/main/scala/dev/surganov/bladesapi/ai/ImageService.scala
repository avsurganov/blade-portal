package dev.surganov.bladesapi.ai

import dev.surganov.bladesapi.ai.client.{CloudinaryClient, DallEClient}
import dev.surganov.bladesapi.ai.models.{BladesImageResponse, ImageData, ImageRequest}
import dev.surganov.bladesapi.common.ErrorResponse
import dev.surganov.bladesapi.config.ConfigProvider
import dev.surganov.bladesapi.util.{JsonSupport, LoggerAccess}
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.{SecurityRequirement, SecurityScheme}
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.ws.rs.{POST, Path}
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.http.scaladsl.model.StatusCodes
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server._
import org.apache.pekko.http.scaladsl.server.directives.Credentials
import org.apache.pekko.stream.Materializer

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

@Path("/api/ai")
@Tag(name = "AI", description = "Operations related to AI image generation")
class ImageService(dallEClient: DallEClient, cloudinaryClient: CloudinaryClient)(implicit
    ec: ExecutionContext,
    mat: Materializer,
    system: ActorSystem[Any]
) extends JsonSupport
    with LoggerAccess
    with ConfigProvider {
  def routes: Route = pathPrefix("ai") { generateImageRoute }

  private def authenticateToken(credentials: Credentials): Option[String] = {
    credentials match {
      case _ @Credentials.Provided(token) if token == config.apiToken => Some(token)
      case _                                                          => None
    }
  }

  @POST
  @Path("/generate-character-image")
  @Operation(
    summary = "Generate an image from a prompt",
    requestBody = new RequestBody(
      content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ImageRequest])))
    ),
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "Image URLs generated",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[BladesImageResponse])))
      ),
      new ApiResponse(
        responseCode = "401",
        description = "Unauthorized",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    ),
    security = Array(new SecurityRequirement(name = "bearerAuth"))
  )
  @SecurityScheme(
    name = "bearerAuth",
    `type` = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
  )
  def generateImageRoute: Route = {
    path("generate-character-image") {
      authenticateOAuth2(realm = config.url, authenticateToken) { _ =>
        post {
          entity(as[ImageRequest]) { request =>
            onComplete(dallEClient.generateCharacterImage(request.characterId, request.prompt)) {
              case Success(imageResponse) =>
                onComplete(cloudinaryClient.processImage(imageResponse.url, request.characterId)) {
                  case Success(cloudinaryUrl) => complete(BladesImageResponse(ImageData(cloudinaryUrl)))
                  case Failure(exception)     => complete(StatusCodes.InternalServerError -> ErrorResponse(message = exception.getMessage))
                }
              case Failure(exception) => complete(StatusCodes.InternalServerError -> ErrorResponse(message = exception.getMessage))
            }
          }
        }
      }
    }
  }
}
