package dev.surganov.bladesapi.util

import dev.surganov.bladesapi.common.ErrorResponse
import org.apache.pekko.http.scaladsl.model._
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.{ExceptionHandler, RejectionHandler}
import play.api.libs.json.Json

object CustomExceptionHandler extends LoggerAccess {
  implicit def customExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case e: NoSuchElementException =>
        extractUri { uri =>
          val msg = s"Resource $uri not found: ${e.getMessage}"
          log.error(msg)
          val errorResponse = ErrorResponse(message = msg)
          complete(
            HttpResponse(
              status = StatusCodes.NotFound,
              entity = HttpEntity(ContentType(MediaTypes.`application/json`), Json.stringify(Json.toJson(errorResponse)))
            )
          )
        }
      case e: IllegalArgumentException =>
        extractUri { _ =>
          val msg = s"Invalid argument: ${e.getMessage}"
          log.error(msg)
          val errorResponse = ErrorResponse(message = msg)
          complete(
            HttpResponse(
              status = StatusCodes.BadRequest,
              entity = HttpEntity(ContentType(MediaTypes.`application/json`), Json.stringify(Json.toJson(errorResponse)))
            )
          )
        }
      case e: Exception =>
        extractUri { uri =>
          val msg = s"Request to $uri could not be handled normally. Error: ${e.getMessage}"
          log.error(msg)
          val errorResponse = ErrorResponse(message = msg)
          complete(
            HttpResponse(
              status = StatusCodes.InternalServerError,
              entity = HttpEntity(ContentType(MediaTypes.`application/json`), Json.stringify(Json.toJson(errorResponse)))
            )
          )
        }
    }

  implicit def customRejectionHandler: RejectionHandler =
    RejectionHandler
      .newBuilder()
      .handleNotFound {
        extractRequest { request =>
          val msg = s"The requested resource [${request.method.value} ${request.uri}] could not be found."
          log.error(msg)
          val errorResponse = ErrorResponse(message = msg)
          complete(
            HttpResponse(
              status = StatusCodes.NotFound,
              entity = HttpEntity(ContentType(MediaTypes.`application/json`), Json.stringify(Json.toJson(errorResponse)))
            )
          )
        }
      }
      .result()
}
