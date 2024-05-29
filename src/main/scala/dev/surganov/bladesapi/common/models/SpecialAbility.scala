package dev.surganov.bladesapi.common.models

import play.api.libs.json.{Format, Json}

case class SpecialAbility(name: String, description: String, pointsRequired: Int = 1)
object SpecialAbility {
  implicit val format: Format[SpecialAbility] = Json.format
}
