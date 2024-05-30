package dev.surganov.bladesapi.crews.models

import play.api.libs.json.{Format, Json}

case class CrewAttributes(
    holdStatusOptions: List[HoldStatus],
    tiers: List[Tier],
    repPoints: Int = 6,
    turfPoints: Int = 6,
    heatPoints: Int = 9,
    wantedLevelPoints: Int = 4
)

object CrewAttributes {
  implicit val format: Format[CrewAttributes] = Json.format[CrewAttributes]
}
