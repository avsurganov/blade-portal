package dev.surganov.bladesapi.common

import play.api.libs.json.{Format, Json}

trait Response

case class StatusResponse(status: String) extends Response
object StatusResponse {
  implicit val format: Format[StatusResponse] = Json.format
}

case class Error(code: Int, message: String) extends Response
object Error {
  implicit val format: Format[Error] = Json.format[Error]
}
