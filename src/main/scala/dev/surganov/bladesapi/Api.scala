package dev.surganov.bladesapi

import dev.surganov.bladesapi.config.ConfigProvider
import dev.surganov.bladesapi.playbooks.PlaybookService
import dev.surganov.bladesapi.swagger.SwaggerDocService
import dev.surganov.bladesapi.util.{HtmlRendererHelper, LoggerAccess}
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.http.cors.scaladsl.CorsDirectives.cors
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import org.apache.pekko.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object Api extends App with LoggerAccess with ConfigProvider {
  implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "blades-api")
  sys.addShutdownHook {
    system.terminate()
    system.whenTerminated
  }

  implicit val ec: ExecutionContext = system.executionContext

  import dev.surganov.bladesapi.util.CustomExceptionHandler._

  private val routes = cors() {
    handleExceptions(customExceptionHandler) {
      handleRejections(customRejectionHandler) {
        pathSingleSlash {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlRendererHelper.renderReadmeToHtml))
        } ~
          pathPrefix("api") {
            PlaybookService.routes
          } ~
          pathPrefix("swagger") {
            getFromResourceDirectory("swagger-ui")
          } ~
          path("swagger") {
            redirect("/swagger/index.html", StatusCodes.PermanentRedirect)
          } ~
          SwaggerDocService.routes
      }
    }
  }

  private val bindingFuture = Http().newServerAt(config.host, config.port).bind(routes)

  bindingFuture.onComplete {
    case scala.util.Success(_) =>
      log.info(s"Server online at ${config.url}")
    case scala.util.Failure(exception) =>
      log.info(s"Failed to bind HTTP endpoint, terminating system: ${exception.getMessage}")
      system.terminate()
  }

  // Prevent the application from terminating immediately
  StdIn.readLine() // Press ENTER to stop the server
  bindingFuture
    .flatMap(_.unbind()) // Trigger unbinding from the port
    .onComplete(_ => system.terminate()) // Shutdown the system
}
