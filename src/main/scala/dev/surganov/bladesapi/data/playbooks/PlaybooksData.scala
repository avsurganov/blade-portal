package dev.surganov.bladesapi.data.playbooks

import dev.surganov.bladesapi.playbooks.models.{Playbook, PlaybookList, PlaybookName}

object PlaybooksData {
  def all: PlaybookList = {
    val playbooks: List[Playbook] = PlaybookName.all.map { name =>
      playbook(name)
    }
    PlaybookList(playbooks)
  }

  def playbook(name: PlaybookName): Playbook = {
    Playbook(
      name = name,
      specialAbilities = SpecialAbilityData.data(name),
      attributes = PlaybookAttributesData.data(name),
      contacts = PlaybookContactData.data(name),
      availableItems = ItemData.data(name),
      additionalAvailableItems = ItemData.defaultItems
    )
  }
}
