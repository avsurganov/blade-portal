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
  }.map { case (name, upgrades) =>
    name -> (upgrades ++ availableUpgradesForCrew(name))
  }

  private def availableUpgradesForCrew(name: CrewName): List[Upgrade] = List[Upgrade](
    // General Upgrades
    Upgrade(
      "Carriage",
      "A vehicle for carrying people or cargo",
      acquired = name match {
        case CrewName.Smugglers => true
        case _                  => false
      }
    ),
    Upgrade("Carriage +2", "Enhanced carriages for increased capacity or quality"),
    Upgrade("Boat", "A vessel for traveling or smuggling via waterways"),
    Upgrade("Boat +2", "Enhanced boats for increased capacity or quality"),
    Upgrade(
      "Hidden",
      "Concealed compartments or rooms within the lair for secrecy",
      acquired = name match {
        case CrewName.Shadows => true
        case _                => false
      }
    ),
    Upgrade("Quarters", "Living spaces for the crew within the lair"),
    Upgrade(
      "Secure",
      "Reinforced doors, locks, and traps to protect the lair",
      acquired = name match {
        case CrewName.Hawkers => true
        case _                => false
      }
    ),
    Upgrade("Secure +2", "Additional reinforcements for superior lair security"),
    Upgrade("Vault", "A secure vault for storing valuables and coin"),
    Upgrade("Vault +2", "Expanded and more secure vault for greater storage capacity"),
    Upgrade("Workshop", "A space for crafting, tinkering, and alchemical work"),
    // Training Upgrades
    Upgrade(
      "Insight Training",
      "Mark 1 additional XP when you train Insight.",
      acquired = name match {
        case CrewName.Assassins => true
        case _                  => false
      }
    ),
    Upgrade(
      "Prowess Training",
      "Mark 1 additional XP when you train Prowess.",
      acquired = name match {
        case CrewName.Assassins | CrewName.Bravos | CrewName.Smugglers | CrewName.Shadows => true
        case _                                                                            => false
      }
    ),
    Upgrade(
      "Resolve Training",
      "Mark 1 additional XP when you train Resolve.",
      acquired = name match {
        case CrewName.Cult | CrewName.Hawkers => true
        case _                                => false
      }
    ),
    Upgrade("Personal Training", "Mark 1 additional XP when you train any attribute."),
    // Quality Upgrades
    Upgrade("Documents", "Secure storage for important papers and records, providing +1d to gather information related to documents."),
    Upgrade("Gear", "Storage for specialized equipment and gear, providing +1d to actions involving this gear."),
    Upgrade(
      "Implements",
      "Storage for tools and devices used in crew operations, providing +1d to craft, repair, or use these implements."
    ),
    Upgrade("Supplies", "Stockpile of necessary supplies and consumables, providing +1d to actions that require specific supplies."),
    Upgrade("Tools", "A collection of tools for various tasks and repairs, providing +1d to actions requiring these tools."),
    Upgrade("Weapon", "Storage and maintenance for the crew's weaponry, providing +1d to actions involving the use of these weapons.")
  )
}
