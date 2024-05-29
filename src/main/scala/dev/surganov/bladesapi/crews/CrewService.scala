package dev.surganov.bladesapi.crews

import dev.surganov.bladesapi.common.ErrorResponse
import dev.surganov.bladesapi.crews.models.{CrewListResponse, CrewName, CrewResponse}
import dev.surganov.bladesapi.data.crews.CrewsData
import dev.surganov.bladesapi.util.{JsonSupport, LoggerAccess}
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.{Operation, Parameter}
import jakarta.ws.rs.{GET, Path}
import org.apache.pekko.http.scaladsl.model.StatusCodes
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server._

import scala.util.{Failure, Success, Try}

@Path("/api/crews")
@Tag(name = "Crews", description = "Operation related to Crews")
object CrewService extends JsonSupport with LoggerAccess {
  def routes: Route = getAllCrews ~ getCrewByName

  @GET
  @Path("/")
  @Operation(
    summary = "Get all crews",
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "All crews retrieved",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[CrewListResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    )
  )
  def getAllCrews: Route = path("crews") {
    get {
      log.info("Retrieving all crews...")
      complete(CrewListResponse(CrewsData.all))
    }
  }

  @GET
  @Path("{name}")
  @Operation(
    summary = "Get crew by name",
    parameters = Array(
      new Parameter(
        name = "name",
        in = ParameterIn.PATH,
        description = "Name of the crew",
        required = true
      )
    ),
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "Crew retrieved",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[CrewResponse])))
      ),
      new ApiResponse(
        responseCode = "400",
        description = "Crew doesn't exist",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    )
  )
  def getCrewByName: Route = path("crews" / Segment) { name =>
    get {
      log.info(s"Retrieving crew: $name...")
      Try { CrewName.withName(name) } match {
        case Success(name) => complete(CrewResponse(CrewsData.crew(name)))
        case Failure(ex) =>
          log.error(ex.getMessage)
          complete((StatusCodes.BadRequest, ErrorResponse(message = s"Crew [$name] doesn't exist")))
      }
    }
  }
}
