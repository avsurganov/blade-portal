package dev.surganov.bladesapi.data.playbooks

import dev.surganov.bladesapi.playbooks.models.{Insight, PlaybookAttributes, PlaybookName, Prowess, Resolve, TraumaCondition}

object PlaybookAttributesData {
  val data: Map[PlaybookName, PlaybookAttributes] = Map[PlaybookName, PlaybookAttributes](
    PlaybookName.Cutter -> PlaybookAttributes(
      insightDefaults = Insight(),
      prowessDefaults = Prowess(skirmish = 2),
      resolveDefaults = Resolve(command = 1),
      traumaConditions = TraumaCondition.all
    ),
    PlaybookName.Hound -> PlaybookAttributes(
      insightDefaults = Insight(hunt = 2, survey = 1),
      prowessDefaults = Prowess(),
      resolveDefaults = Resolve(),
      traumaConditions = TraumaCondition.all
    ),
    PlaybookName.Leech -> PlaybookAttributes(
      insightDefaults = Insight(tinker = 2),
      prowessDefaults = Prowess(wreck = 1),
      resolveDefaults = Resolve(),
      traumaConditions = TraumaCondition.all
    ),
    PlaybookName.Lurk -> PlaybookAttributes(
      insightDefaults = Insight(),
      prowessDefaults = Prowess(finnesse = 1, prowl = 2),
      resolveDefaults = Resolve(),
      traumaConditions = TraumaCondition.all
    ),
    PlaybookName.Slide -> PlaybookAttributes(
      insightDefaults = Insight(),
      prowessDefaults = Prowess(),
      resolveDefaults = Resolve(consort = 1, sway = 2),
      traumaConditions = TraumaCondition.all
    ),
    PlaybookName.Spider -> PlaybookAttributes(
      insightDefaults = Insight(study = 1),
      prowessDefaults = Prowess(),
      resolveDefaults = Resolve(consort = 2),
      traumaConditions = TraumaCondition.all
    ),
    PlaybookName.Whisper -> PlaybookAttributes(
      insightDefaults = Insight(study = 1),
      prowessDefaults = Prowess(),
      resolveDefaults = Resolve(attune = 2),
      traumaConditions = TraumaCondition.all
    )
  )
}
