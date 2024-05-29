package dev.surganov.bladesapi.crews.models

import play.api.libs.json.{Format, Json}

case class Upgrade(name: String, description: String, acquired: Boolean = false, cost: Int = 1)
object Upgrade {
  implicit val format: Format[Upgrade] = Json.format[Upgrade]
}
