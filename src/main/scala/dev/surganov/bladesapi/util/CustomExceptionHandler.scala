package dev.surganov.bladesapi.util

import dev.surganov.bladesapi.common.ErrorResponse
import org.apache.pekko.http.scaladsl.model._
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.{AuthenticationFailedRejection, AuthorizationFailedRejection, ExceptionHandler, RejectionHandler}
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
      .handle {
        case AuthorizationFailedRejection =>
          extractUri { uri =>
            val msg = s"Authorization failed for resource $uri"
            log.error(msg)
            val errorResponse = ErrorResponse(message = msg)
            complete(
              HttpResponse(
                status = StatusCodes.Unauthorized,
                entity = HttpEntity(ContentType(MediaTypes.`application/json`), Json.stringify(Json.toJson(errorResponse)))
              )
            )
          }
        case AuthenticationFailedRejection(cause, _) =>
          extractUri { uri =>
            val msg = cause match {
              case AuthenticationFailedRejection.CredentialsMissing =>
                s"Credentials are missing for accessing resource $uri"
              case AuthenticationFailedRejection.CredentialsRejected =>
                s"Credentials are rejected for accessing resource $uri"
            }
            log.error(msg)
            val errorResponse = ErrorResponse(message = msg)
            complete(
              HttpResponse(
                status = StatusCodes.Unauthorized,
                entity = HttpEntity(ContentType(MediaTypes.`application/json`), Json.stringify(Json.toJson(errorResponse)))
              )
            )
          }
      }
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
