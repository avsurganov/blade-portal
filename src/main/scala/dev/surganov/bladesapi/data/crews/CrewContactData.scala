package dev.surganov.bladesapi.data.crews

import dev.surganov.bladesapi.common.models.Contact
import dev.surganov.bladesapi.crews.models.CrewName

object CrewContactData {
  val data: Map[CrewName, List[Contact]] = Map[CrewName, List[Contact]](
    CrewName.Assassins -> List[Contact](
      Contact("Trev", "a gang boss"),
      Contact("Lydra", "a deal broker"),
      Contact("Irimina", "a vicious noble"),
      Contact("Karlos", "a bounty hunter"),
      Contact("Exeter", "a spirit warden"),
      Contact("Sevoy", "a merchant lord")
    ),
    CrewName.Bravos -> List[Contact](
      Contact("Meg", "a pit-fighter"),
      Contact("Conway", "a bluecoat"),
      Contact("Keller", "a blacksmith"),
      Contact("Tomas", "a physicker"),
      Contact("Walker", "a ward boss"),
      Contact("Lutes", "a tavern owner")
    ),
    CrewName.Cult -> List[Contact](
      Contact("Gagan", "an academic"),
      Contact("Adikin", "an occultist"),
      Contact("Hutchins", "an antiquarian"),
      Contact("Moriya", "a spirit trafficker"),
      Contact("Mateas Kline", "a noble"),
      Contact("Bennett", "an astronomer")
    ),
    CrewName.Hawkers -> List[Contact](
      Contact("Rolan Wott", "a magistrate"),
      Contact("Laroze", "a bluecoat"),
      Contact("Lydra", "a deal broker"),
      Contact("Hoxley", "a smuggler"),
      Contact("Anya", "a dilettante"),
      Contact("Marlo", "a gang boss")
    ),
    CrewName.Smugglers -> List[Contact](
      Contact("Elynn", "a dock worker"),
      Contact("Rolan", "a drug dealer"),
      Contact("Sera", "an arms dealer"),
      Contact("Nyelle", "a spirit trafficker"),
      Contact("Decker", "an anarchist"),
      Contact("Esme", "a tavern owner")
    ),
    CrewName.Shadows -> List[Contact](
      Contact("Dowler", "an explorer"),
      Contact("Laroze", "a bluecoat"),
      Contact("Amancio", "a deal broker"),
      Contact("Fitz", "a collector"),
      Contact("Adelaide Phroaig", "a noble"),
      Contact("Rigney", "a tavern owner")
    )
  )
}
