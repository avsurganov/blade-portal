package dev.surganov.bladesapi.playbooks.models

import play.api.libs.json.{Format, Json}

case class PlaybookContact(name: String, occupation: String)
object PlaybookContact {
  implicit val format: Format[PlaybookContact] = Json.format
}
