package dev.surganov.bladesapi.data.playbooks

import dev.surganov.bladesapi.common.models.Contact
import dev.surganov.bladesapi.playbooks.models.PlaybookName

object PlaybookContactData {
  val data: Map[PlaybookName, List[Contact]] = Map[PlaybookName, List[Contact]](
    PlaybookName.Cutter -> List[Contact](
      Contact("Marlane", "a pugilist"),
      Contact("Chael", "a vicious thug"),
      Contact("Mercy", "a cold killer"),
      Contact("Grace", "an extortionist"),
      Contact("Sawtooth", "a physicker")
    ),
    PlaybookName.Hound -> List[Contact](
      Contact("Steiner", "an assassin"),
      Contact("Celene", "a sentinel"),
      Contact("Melvir", "a physicker"),
      Contact("Veleris", "a spy"),
      Contact("Casta", "a bounty hunter")
    ),
    PlaybookName.Leech -> List[Contact](
      Contact("Stazia", "an apothecary"),
      Contact("Veldren", "a psychonaut"),
      Contact("Eckerd", "a corpse thief"),
      Contact("Jul", "a blood dealer"),
      Contact("Malista", "a priestess")
    ),
    PlaybookName.Lurk -> List[Contact](
      Contact("Telda", "a beggar"),
      Contact("Darmot", "a bluecoat"),
      Contact("Frake", "a locksmith"),
      Contact("Roslyn Kellis", "a noble"),
      Contact("Petra", "a city clerk")
    ),
    PlaybookName.Slide -> List[Contact](
      Contact("Bryl", "a drug dealer"),
      Contact("Bazso Baz", "a gang leader"),
      Contact("Klyra", "a tavern owner"),
      Contact("Nyryx", "a prostitute"),
      Contact("Harker", "a jail-bird")
    ),
    PlaybookName.Spider -> List[Contact](
      Contact("Salia", "an information broker"),
      Contact("Augus", "a master architect"),
      Contact("Jennah", "a servant"),
      Contact("Riven", "a chemist"),
      Contact("Jeren", "a bluecoat archivist")
    ),
    PlaybookName.Whisper -> List[Contact](
      Contact("Nyryx", "a possessor ghost"),
      Contact("Scurlock", "a vampire"),
      Contact("Setarra", "a demon"),
      Contact("Quellyn", "a witch"),
      Contact("Flint", "a spirit trafficker")
    )
  )
}
