package dev.surganov.bladesapi.common

import enumeratum.{Enum, EnumEntry, PlayJsonEnum}
import play.api.libs.json.{Format, JsResult, JsValue, Json}

import scala.collection.immutable

sealed trait ResponseStatus extends EnumEntry

object ResponseStatus extends Enum[ResponseStatus] with PlayJsonEnum[ResponseStatus] {
  val values: immutable.IndexedSeq[ResponseStatus] = findValues

  case object Success extends ResponseStatus
  case object Error extends ResponseStatus
}

trait Response {
  def status: ResponseStatus
}

case class ErrorResponse(status: ResponseStatus = ResponseStatus.Error, message: String) extends Response
object ErrorResponse {
  implicit val format: Format[ErrorResponse] = Json.format[ErrorResponse]
}

abstract class SuccessResponse[T](val status: ResponseStatus = ResponseStatus.Success, val details: T)
object SuccessResponse {
  implicit def format[T: Format]: Format[SuccessResponse[T]] = new Format[SuccessResponse[T]] {
    def writes(o: SuccessResponse[T]): JsValue = {
      Json.obj(
        "status" -> o.status,
        "details" -> Json.toJson(o.details)
      )
    }

    def reads(json: JsValue): JsResult[SuccessResponse[T]] = {
      for {
        status <- (json \ "status").validate[ResponseStatus]
        details <- (json \ "details").validate[T]
      } yield new SuccessResponse[T](status, details) {}
    }
  }
}

object SuccessResponseFormat {
  def format[T: Format, R <: SuccessResponse[T]](construct: T => R): Format[R] = new Format[R] {
    def writes(o: R): JsValue = SuccessResponse.format[T].writes(o)

    def reads(json: JsValue): JsResult[R] = SuccessResponse.format[T].reads(json).map { sr: SuccessResponse[T] =>
      construct(sr.details)
    }
  }
}
