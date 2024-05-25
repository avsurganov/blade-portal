package dev.surganov.bladesapi.playbooks.models

import play.api.libs.json.{Format, Json}

case class Playbook(
    name: PlaybookName,
    specialAbilities: List[SpecialAbility]
)
object Playbook {
  implicit val format: Format[Playbook] = Json.format
}

case class PlaybookList(
    playbooks: List[Playbook],
    heritageOptions: List[Heritage] = Heritage.all,
    backgroundOptions: List[Background] = Background.all,
    viceOptions: List[Vice] = Vice.all
)
object PlaybookList {
  implicit val format: Format[PlaybookList] = Json.format
}
