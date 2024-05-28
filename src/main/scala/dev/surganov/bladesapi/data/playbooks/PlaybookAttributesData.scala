package dev.surganov.bladesapi.data.playbooks

import dev.surganov.bladesapi.playbooks.models.{Insight, PlaybookAttributes, PlaybookName, Prowess, Resolve}

object PlaybookAttributesData {
  val data: Map[PlaybookName, PlaybookAttributes] = Map[PlaybookName, PlaybookAttributes](
    PlaybookName.Cutter -> PlaybookAttributes(
      insightDefaults = Insight(),
      prowessDefaults = Prowess(skirmish = 2),
      resolveDefaults = Resolve(command = 1),
    ),
    PlaybookName.Hound -> PlaybookAttributes(
      insightDefaults = Insight(hunt = 2, survey = 1),
      prowessDefaults = Prowess(),
      resolveDefaults = Resolve(),
    ),
    PlaybookName.Leech -> PlaybookAttributes(
      insightDefaults = Insight(tinker = 2),
      prowessDefaults = Prowess(wreck = 1),
      resolveDefaults = Resolve(),
    ),
    PlaybookName.Lurk -> PlaybookAttributes(
      insightDefaults = Insight(),
      prowessDefaults = Prowess(finnesse = 1, prowl = 2),
      resolveDefaults = Resolve(),
    ),
    PlaybookName.Slide -> PlaybookAttributes(
      insightDefaults = Insight(),
      prowessDefaults = Prowess(),
      resolveDefaults = Resolve(consort = 1, sway = 2),
    ),
    PlaybookName.Spider -> PlaybookAttributes(
      insightDefaults = Insight(study = 1),
      prowessDefaults = Prowess(),
      resolveDefaults = Resolve(consort = 2),
    ),
    PlaybookName.Whisper -> PlaybookAttributes(
      insightDefaults = Insight(study = 1),
      prowessDefaults = Prowess(),
      resolveDefaults = Resolve(attune = 2),
    )
  )
}
