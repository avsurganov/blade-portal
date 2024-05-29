package dev.surganov.bladesapi.data.crews

import dev.surganov.bladesapi.crews.models.{Crew, CrewList, CrewName}

object CrewsData {
  def all: CrewList = {
    CrewList(
      CrewName.all.map { name =>
        crew(name)
      }
    )
  }

  def crew(name: CrewName): Crew = {
    Crew(name, CrewSpecialAbilityData.data(name))
  }
}
