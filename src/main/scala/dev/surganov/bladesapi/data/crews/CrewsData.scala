package dev.surganov.bladesapi.data.crews

import dev.surganov.bladesapi.crews.models.{Crew, CrewAttributes, CrewList, CrewName, HoldStatus, Reputation, Tier}

object CrewsData {
  def all: CrewList = {
    CrewList(
      CrewName.all.map { name =>
        crew(name)
      }
    )
  }

  def crew(name: CrewName): Crew = {
    Crew(
      name = name,
      reputations = Reputation.all,
      specialAbilities = CrewSpecialAbilityData.data(name),
      contacts = CrewContactData.data(name),
      upgrades = CrewUpgradeData.data(name),
      attributes = CrewAttributes(HoldStatus.all, Tier.all),
      cohorts = CrewUpgradeData.cohorts(name)
    )
  }
}
