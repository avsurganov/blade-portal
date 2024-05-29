package dev.surganov.bladesapi.playbooks.models
import play.api.libs.json.{Json, OFormat}

case class PlaybookOptions(
    heritageOptions: List[Heritage],
    backgroundOptions: List[Background],
    viceOptions: List[Vice],
)
object PlaybookOptions {
  implicit val format: OFormat[PlaybookOptions] = Json.format
}
