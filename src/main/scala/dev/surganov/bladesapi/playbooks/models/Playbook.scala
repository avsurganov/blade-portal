package dev.surganov.bladesapi.playbooks.models

import play.api.libs.json.{Format, Json}

case class Playbook(
    name: PlaybookName,
    specialAbilities: List[SpecialAbility],
    options: PlaybookOptions = PlaybookOptions()
)
object Playbook {
  implicit val format: Format[Playbook] = Json.format
}

case class PlaybookList(
    playbooks: List[Playbook]
)
object PlaybookList {
  implicit val format: Format[PlaybookList] = Json.format
}

case class PlaybookOptions(
    heritageOptions: List[String] = Heritage.all.map(_.toString),
    backgroundOptions: List[String] = Background.all.map(_.toString),
    viceOptions: List[String] = Vice.all.map(_.toString)
)
object PlaybookOptions {
  implicit val format: Format[PlaybookOptions] = Json.format
}
