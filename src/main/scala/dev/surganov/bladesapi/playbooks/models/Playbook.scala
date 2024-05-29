package dev.surganov.bladesapi.playbooks.models

import dev.surganov.bladesapi.common.models.SpecialAbility
import dev.surganov.bladesapi.common.{SuccessResponse, SuccessResponseFormat}
import play.api.libs.json.{Format, Json}

case class Playbook(
    name: PlaybookName,
    specialAbilities: List[SpecialAbility],
    attributes: PlaybookAttributes,
    contacts: List[PlaybookContact],
    availableItems: List[Item],
    additionalAvailableItems: List[Item],
    options: PlaybookOptions = PlaybookOptions(heritageOptions = Heritage.all, backgroundOptions = Background.all, viceOptions = Vice.all)
)
object Playbook {
  implicit val format: Format[Playbook] = Json.format
}

case class PlaybookList(
    playbooks: List[Playbook],
)
object PlaybookList {
  implicit val format: Format[PlaybookList] = Json.format
}

case class PlaybookResponse(override val details: Playbook) extends SuccessResponse[Playbook](details = details)
object PlaybookResponse {
  implicit val format: Format[PlaybookResponse] = SuccessResponseFormat.format(PlaybookResponse.apply)
}

case class PlaybookListResponse(override val details: PlaybookList) extends SuccessResponse[PlaybookList](details = details)
object PlaybookListResponse {
  implicit val format: Format[PlaybookListResponse] = SuccessResponseFormat.format(PlaybookListResponse.apply)
}
