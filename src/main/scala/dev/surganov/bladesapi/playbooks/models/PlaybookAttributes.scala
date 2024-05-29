package dev.surganov.bladesapi.playbooks.models

import play.api.libs.json.{Format, Json}

case class PlaybookAttributes(
    insightDefaults: Insight,
    prowessDefaults: Prowess,
    resolveDefaults: Resolve,
    traumaConditions: List[TraumaCondition],
    stressPoints: Int = 9,
    traumaPoints: Int = 4,
    playbookExpPoints: Int = 9,
    attributesExpPoints: Int = 6
)
object PlaybookAttributes {
  implicit val format: Format[PlaybookAttributes] = Json.format
}

case class Insight(
    hunt: Int = 0,
    study: Int = 0,
    survey: Int = 0,
    tinker: Int = 0
)
object Insight {
  implicit val format: Format[Insight] = Json.format
}

case class Prowess(
    finnesse: Int = 0,
    prowl: Int = 0,
    skirmish: Int = 0,
    wreck: Int = 0
)
object Prowess {
  implicit val format: Format[Prowess] = Json.format
}

case class Resolve(
    attune: Int = 0,
    command: Int = 0,
    consort: Int = 0,
    sway: Int = 0
)
object Resolve {
  implicit val format: Format[Resolve] = Json.format
}
