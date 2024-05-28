package dev.surganov.bladesapi.data.playbooks

import dev.surganov.bladesapi.playbooks.models.{Item, Playbook, PlaybookList, PlaybookName, SpecialAbility}

object PlaybooksData {
  def all: PlaybookList = {
    val playbooks: List[Playbook] = PlaybookName.all.map { name =>
      playbook(name)
    }
    PlaybookList(playbooks)
  }

  def playbook(name: PlaybookName): Playbook = {
    Playbook(name, SpecialAbilityData.data(name), PlaybookContactData.data(name), ItemData.data(name), ItemData.defaultItems)
  }
}
