package dev.surganov.bladesapi.playbooks.models

import play.api.libs.json.{Format, Json}

case class PlaybookOptions(
    heritageOptions: List[String] = Heritage.all.map(_.toString),
    backgroundOptions: List[String] = Background.all.map(_.toString),
    viceOptions: List[String] = Vice.all.map(_.toString)
)
object PlaybookOptions {
  implicit val format: Format[PlaybookOptions] = Json.format
}
