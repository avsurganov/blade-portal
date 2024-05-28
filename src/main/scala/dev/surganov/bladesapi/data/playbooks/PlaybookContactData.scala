package dev.surganov.bladesapi.data.playbooks

import dev.surganov.bladesapi.playbooks.models.{PlaybookContact, PlaybookName}

object PlaybookContactData {
  val data: Map[PlaybookName, List[PlaybookContact]] = Map[PlaybookName, List[PlaybookContact]](
    PlaybookName.Cutter -> List[PlaybookContact](
      PlaybookContact("Marlane", "a pugilist"),
      PlaybookContact("Chael", "a vicious thug"),
      PlaybookContact("Mercy", "a cold killer"),
      PlaybookContact("Grace", "an extortionist"),
      PlaybookContact("Sawtooth", "a physicker")
    ),
    PlaybookName.Hound -> List[PlaybookContact](
      PlaybookContact("Steiner", "an assassin"),
      PlaybookContact("Celene", "a sentinel"),
      PlaybookContact("Melvir", "a physicker"),
      PlaybookContact("Veleris", "a spy"),
      PlaybookContact("Casta", "a bounty hunter")
    ),
    PlaybookName.Leech -> List[PlaybookContact](
      PlaybookContact("Stazia", "an apothecary"),
      PlaybookContact("Veldren", "a psychonaut"),
      PlaybookContact("Eckerd", "a corpse thief"),
      PlaybookContact("Jul", "a blood dealer"),
      PlaybookContact("Malista", "a priestess")
    ),
    PlaybookName.Lurk -> List[PlaybookContact](
      PlaybookContact("Telda", "a beggar"),
      PlaybookContact("Darmot", "a bluecoat"),
      PlaybookContact("Frake", "a locksmith"),
      PlaybookContact("Roslyn Kellis", "a noble"),
      PlaybookContact("Petra", "a city clerk")
    ),
    PlaybookName.Slide -> List[PlaybookContact](
      PlaybookContact("Bryl", "a drug dealer"),
      PlaybookContact("Bazso Baz", "a gang leader"),
      PlaybookContact("Klyra", "a tavern owner"),
      PlaybookContact("Nyryx", "a prostitute"),
      PlaybookContact("Harker", "a jail-bird")
    ),
    PlaybookName.Spider -> List[PlaybookContact](
      PlaybookContact("Salia", "an information broker"),
      PlaybookContact("Augus", "a master architect"),
      PlaybookContact("Jennah", "a servant"),
      PlaybookContact("Riven", "a chemist"),
      PlaybookContact("Jeren", "a bluecoat archivist")
    ),
    PlaybookName.Whisper -> List[PlaybookContact](
      PlaybookContact("Nyryx", "a possessor ghost"),
      PlaybookContact("Scurlock", "a vampire"),
      PlaybookContact("Setarra", "a demon"),
      PlaybookContact("Quellyn", "a witch"),
      PlaybookContact("Flint", "a spirit trafficker")
    )
  )
}
