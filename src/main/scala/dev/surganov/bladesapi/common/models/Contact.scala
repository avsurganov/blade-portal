package dev.surganov.bladesapi.common.models

import play.api.libs.json.{Format, Json}

case class Contact(name: String, occupation: String)
object Contact {
  implicit val format: Format[Contact] = Json.format
}
