package dev.surganov.bladesapi

import dev.surganov.bladesapi.config.ConfigProvider
import dev.surganov.bladesapi.util.{JsonContentTypeSupport, LoggerAccess}
import org.apache.pekko.http.scaladsl.Http

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Api extends ApiModule with App with LoggerAccess with ConfigProvider with JsonContentTypeSupport {
  sys.addShutdownHook {
    log.info("Shutdown hook triggered, terminating the system.")
    terminate()
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
