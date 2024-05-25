package dev.surganov.bladesapi.util

import dev.surganov.bladesapi.common.Error
import org.apache.pekko.http.scaladsl.model.StatusCodes
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.{ExceptionHandler, Route}
import play.api.libs.json.Json

object CustomExceptionHandler extends JsonSupport {
  def exceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case ex: NoSuchElementException =>
        extractUri { uri =>
          complete(StatusCodes.NotFound, Json.toJson(Error(404, s"Resource not found: ${uri.path}")))
        }
      case ex: Exception =>
        extractUri { _ =>
          complete(StatusCodes.InternalServerError, Json.toJson(Error(500, s"Internal server error: ${ex.getMessage}")))
        }
    }
}
