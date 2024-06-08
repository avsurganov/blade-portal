package dev.surganov.bladesapi

import com.cloudinary.Cloudinary
import com.softwaremill.macwire.wire
import dev.surganov.bladesapi.cohorts.CohortService
import dev.surganov.bladesapi.config.ConfigProvider
import dev.surganov.bladesapi.crews.CrewService
import dev.surganov.bladesapi.ai.ImageService
import dev.surganov.bladesapi.ai.client.{CloudinaryClient, DallEClient}
import dev.surganov.bladesapi.playbooks.PlaybookService
import dev.surganov.bladesapi.swagger.SwaggerDocService
import dev.surganov.bladesapi.util.{CustomExceptionHandler, HtmlRendererHelper, JsonContentTypeSupport, LoggerAccess}
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.http.cors.scaladsl.CorsDirectives.cors
import org.apache.pekko.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.stream.Materializer

import scala.concurrent.ExecutionContext

trait ApiModule extends LoggerAccess with JsonContentTypeSupport with CloudinaryProvider {
  implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "blades-api")
  implicit val materializer: Materializer = Materializer(system)
  implicit val ec: ExecutionContext = system.executionContext

  lazy val dallEClient = new DallEClient()

  lazy val cloudinary: Cloudinary = cloudinaryApi

  private lazy val cloudinaryClient: CloudinaryClient = wire[CloudinaryClient]
  private lazy val dallEService: ImageService = wire[ImageService]
  private lazy val playbookService = wire[PlaybookService]
  private lazy val crewService = wire[CrewService]
  private lazy val cohortService = wire[CohortService]
  private lazy val swaggerDocService = wire[SwaggerDocService]

  lazy val routes: Route = cors() {
    handleExceptions(CustomExceptionHandler.customExceptionHandler) {
      handleRejections(CustomExceptionHandler.customRejectionHandler) {
        pathSingleSlash {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlRendererHelper.renderReadmeToHtml))
        } ~
          pathPrefix("api") {
            json {
              playbookService.routes ~
                crewService.routes ~
                cohortService.routes ~
                dallEService.routes
            }
          } ~
          pathPrefix("swagger") {
            getFromResourceDirectory("swagger-ui")
          } ~
          path("swagger") {
            redirect("/swagger/index.html", StatusCodes.PermanentRedirect)
          } ~
          swaggerDocService.routes
      }
    }
  }
}

trait CloudinaryProvider extends ConfigProvider {
  def cloudinaryApi: Cloudinary = new Cloudinary(config.cloudinaryUrl)
}
