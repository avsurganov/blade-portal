package dev.surganov.bladesapi.data.crews

import dev.surganov.bladesapi.crews.models.{CrewName, Upgrade}

object CrewUpgradeData {
  val data: Map[CrewName, List[Upgrade]] = Map[CrewName, List[Upgrade]](
    CrewName.Assassins -> List[Upgrade](
      Upgrade("Assassin Rigging", "2 free load of weapons or gear"),
      Upgrade("Ironhook Contacts", "+1 Tier in prison"),
      Upgrade("Elite Skulks", "Higher quality cohorts specializing in stealth and infiltration"),
      Upgrade("Elite Thugs", "Higher quality cohorts specializing in combat and intimidation")
    ),
    CrewName.Bravos -> List[Upgrade](
      Upgrade("Bravos Rigging", "2 free load of weapons or armor"),
      Upgrade("Ironhook Contacts", "+1 Tier in prison"),
      Upgrade("Elite Rovers", "Higher quality cohorts specializing in transport and mobility"),
      Upgrade("Elite Thugs", "Higher quality cohorts specializing in combat and intimidation")
    ),
    CrewName.Cult -> List[Upgrade](
      Upgrade("Cult Rigging", "2 free load of documents or implements"),
      Upgrade("Ritual Sanctum", "A dedicated space in the lair for performing rituals"),
      Upgrade("Elite Adepts", "Higher quality cohorts specializing in arcane knowledge and rituals"),
      Upgrade("Elite Thugs", "Higher quality cohorts specializing in combat and intimidation")
    ),
    CrewName.Hawkers -> List[Upgrade](
      Upgrade("Hawkers Rigging", "1 carried item is concealed and has no load"),
      Upgrade("Ironhook Contacts", "+1 Tier in prison"),
      Upgrade("Elite Rooks", "Higher quality cohorts specializing in deception and stealth"),
      Upgrade("Elite Thugs", "Higher quality cohorts specializing in combat and intimidation")
    ),
    CrewName.Smugglers -> List[Upgrade](
      Upgrade("Smugglers Rigging", "2 items carried are perfectly concealed"),
      Upgrade("Camouflage", "Vehicles are perfectly concealed at rest"),
      Upgrade("Elite Rovers", "Higher quality cohorts specializing in transport and mobility"),
      Upgrade("Barge", "Increases mobility for the lair")
    ),
    CrewName.Shadows -> List[Upgrade](
      Upgrade("Thief Rigging", "2 free load of tools or gear"),
      Upgrade("Underground Maps & Passkeys", "Access to hidden passages and locked areas"),
      Upgrade("Elite Rooks", "Higher quality cohorts specializing in deception and stealth"),
      Upgrade("Elite Skulks", "Higher quality cohorts specializing in stealth and infiltration")
    )
  ).map { case (name, upgrades) =>
    name -> (upgrades :+ Upgrade(
      name = "Sturdy",
      description = "+1 to trauma cap",
      cost = 3
    ))
  }
}
