package dev.surganov.bladesapi.playbooks.models

import dev.surganov.bladesapi.common.{SuccessResponse, SuccessResponseFormat}
import play.api.libs.json.{Format, JsResult, JsValue, Json}

case class Playbook(
    name: PlaybookName,
    specialAbilities: List[SpecialAbility],
    options: PlaybookOptions = PlaybookOptions()
)
object Playbook {
  implicit val format: Format[Playbook] = Json.format
}

case class PlaybookOptions(
    heritageOptions: List[String] = Heritage.all.map(_.toString),
    backgroundOptions: List[String] = Background.all.map(_.toString),
    viceOptions: List[String] = Vice.all.map(_.toString)
)
object PlaybookOptions {
  implicit val format: Format[PlaybookOptions] = Json.format
}

case class PlaybookResponse(override val details: Playbook) extends SuccessResponse[Playbook](details = details)
object PlaybookResponse {
  implicit val format: Format[PlaybookResponse] = SuccessResponseFormat.format(PlaybookResponse.apply)
}

case class PlaybookListResponse(override val details: List[Playbook]) extends SuccessResponse[List[Playbook]](details = details)
object PlaybookListResponse {
  implicit val format: Format[PlaybookListResponse] = SuccessResponseFormat.format(PlaybookListResponse.apply)
}
