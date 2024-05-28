package dev.surganov.bladesapi.playbooks.models

import play.api.libs.json.{Format, Json}

case class Item(name: String, description: String, load: Int = 1)
object Item {
  implicit val format: Format[Item] = Json.format
}
