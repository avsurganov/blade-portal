package dev.surganov.bladesapi

import dev.surganov.bladesapi.swagger.SwaggerDocService
import dev.surganov.bladesapi.util.LoggerAccess
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.cors.scaladsl.CorsDirectives.cors
import org.apache.pekko.http.scaladsl.server.Directives._
import scala.concurrent.ExecutionContext

object App extends AppRoutes with LoggerAccess {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "blades-api")
    sys.addShutdownHook(system.terminate())

    implicit val ec: ExecutionContext = system.executionContext

    val routes = cors()(aggregatedRoutes ~ SwaggerDocService.routes)

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

    bindingFuture.onComplete {
      case scala.util.Success(_) =>
        log.info(s"Server online at http://localhost:8080/")
      case scala.util.Failure(exception) =>
        log.info(s"Failed to bind HTTP endpoint, terminating system: ${exception.getMessage}")
        system.terminate()
    }
  }
}
