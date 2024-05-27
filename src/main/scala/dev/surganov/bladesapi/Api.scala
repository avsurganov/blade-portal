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

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}
import scala.io.StdIn

object Api extends App with LoggerAccess with ConfigProvider {
  implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "blades-api")
  implicit val ec: ExecutionContext = system.executionContext

  sys.addShutdownHook {
    log.info("Shutdown hook triggered, terminating the system.")
    terminate()
  }

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
    case scala.util.Success(binding) =>
      log.info(s"Server online at [${config.host}:${config.port}] - ${config.url}")
      // Log bound address
      log.info(s"Bound to ${binding.localAddress}")
    case scala.util.Failure(exception) =>
      log.error(s"Failed to bind HTTP endpoint, terminating system: ${exception.getMessage}")
      system.terminate()
  }

  // Prevent the application from terminating immediately by awaiting termination
  Await.result(system.whenTerminated, Duration.Inf)

  private def terminate(): Unit = {
    system.terminate()
    system.whenTerminated.onComplete {
      case scala.util.Success(_) =>
        log.info("Actor system terminated successfully.")
      case scala.util.Failure(exception) =>
        log.error(s"Actor system termination failed: ${exception.getMessage}")
    }
  }
}
