package dev.surganov.bladesapi.data.crews

import dev.surganov.bladesapi.common.models.SpecialAbility
import dev.surganov.bladesapi.crews.models.CrewName

object CrewSpecialAbilityData {
  val data: Map[CrewName, List[SpecialAbility]] = Map[CrewName, List[SpecialAbility]](
    CrewName.Assassins -> List[SpecialAbility](
      SpecialAbility("Deadly", "Each PC may add +1 action rating to Hunt, Prowl, or Skirmish (up to a max rating of 3)."),
      SpecialAbility(
        "Crow's Veil",
        "Due to hard-won experience or occult ritual, your activities are hidden from the notice of the death-seeker crows. You don't take extra heat when killing is involved on a score."
      ),
      SpecialAbility(
        "Emberdeath",
        "Due to hard-won experience or occult ritual, you know the arcane method to destroy a living victim's spirit at the moment you kill them. Take 3 stress to channel electroplasmic energy from the ghost field to disintegrate the spirit and dead body in a shower of sparking embers."
      ),
      SpecialAbility(
        "No Traces",
        "When you keep an operation quiet or make it look like an accident, you get half the rep value of the target (round up) instead of zero. When you end downtime with zero heat, take +1 rep."
      ),
      SpecialAbility(
        "Patron",
        "When you advance your Tier, it costs half the coin it normally would. Who is your patron? Why do they help you?"
      ),
      SpecialAbility("Predators", "When you use stealth or subterfuge to commit murder, take +1d to the engagement roll."),
      SpecialAbility(
        "Vipers",
        "When you acquire or craft poisons, you get +1 result level to your roll. When you employ a poison, you are specially prepared to be immune to its effects."
      ),
      SpecialAbility(
        name = "Veteran",
        description = "Choose a special ability from another crew.",
        pointsRequired = 3
      )
    ),
    CrewName.Bravos -> List[SpecialAbility](
      SpecialAbility("Dangerous", "Each PC may add +1 action rating to Hunt, Skirmish, or Wreck (up to a max rating of 3)."),
      SpecialAbility(
        "Blood Brothers",
        "When you fight alongside your cohorts in combat, they get +1d for teamwork rolls (setup and group actions). All of your cohorts get the Thugs type for free (if they're already Thugs, add another type)."
      ),
      SpecialAbility("Door Kickers", "When you execute an assault plan, take +1d to the engagement roll."),
      SpecialAbility("Fiends", "Fear is as good as respect. You may count each wanted level as if it was turf."),
      SpecialAbility("Forged in the Fire", "Each PC has been toughened by cruel experience. You get +1d to resistance rolls."),
      SpecialAbility(
        "Patron",
        "When you advance your Tier, it costs half the coin it normally would. Who is your patron? Why do they help you?"
      ),
      SpecialAbility(
        "War Dogs",
        "When you’re at war (-3 faction status), your crew does not suffer -1 hold and PCs still get two downtime activities, instead of just one."
      ),
      SpecialAbility("Veteran", "Choose a special ability from another crew.", 2)
    ),
    CrewName.Cult -> List[SpecialAbility](
      SpecialAbility("Chosen", "Each PC may add +1 action rating to Attune, Study, or Sway (up to a max rating of 3)."),
      SpecialAbility(
        "Anointed",
        "You get +1d to resistance rolls against supernatural threats. You get +1d to healing rolls when you have supernatural harm."
      ),
      SpecialAbility(
        "Bound in Darkness",
        "You may use teamwork with any cult member, regardless of the distance separating you. By taking 1 stress, your whispered message is heard by every cultist."
      ),
      SpecialAbility(
        "Conviction",
        "Each PC gains an additional Vice: Worship. When you indulge this vice and bring a pleasing sacrifice, you don't overindulge if you clear excess stress. In addition, your deity will assist any one action roll you make—from now until you indulge this vice again."
      ),
      SpecialAbility(
        "Glory Incarnate",
        "Your deity sometimes manifests in the physical world. This can be a great boon, but the priorities and values of a god are not those of mortals. You have been warned."
      ),
      SpecialAbility("Sealed in Blood", "Each human sacrifice yields -3 stress cost for any ritual you perform."),
      SpecialAbility(
        "Zealotry",
        "Your cohorts have abandoned their reason to devote themselves to the cult. They will undertake any service, no matter how dangerous or strange. They gain +1d to rolls against enemies of the faith."
      ),
      SpecialAbility("Veteran", "Choose a special ability from another crew.")
    ),
    CrewName.Hawkers -> List[SpecialAbility](
      SpecialAbility("Silver Tongues", "Each PC may add +1 action rating to Command, Consort, or Sway (up to a max rating of 3)."),
      SpecialAbility(
        "Accord",
        "Sometimes friends are as good as territory. You may count up to three +3 faction statuses you hold as if they are turf."
      ),
      SpecialAbility(
        "The Good Stuff",
        "Your merchandise is exquisite. The product quality is equal to your Tier+2. When you deal with a crew or faction, the GM will tell you who among them is hooked on your product (one, a few, many, or all)."
      ),
      SpecialAbility(
        "Ghost Market",
        "Through arcane ritual or hard-won experience, you have discovered how to prepare your product for sale to ghosts and/or demons. They do not pay in coin. What do they pay with?"
      ),
      SpecialAbility(
        "High Society",
        "It's all about who you know. Take -1 heat during downtime and +1d to gather info about the city's elite."
      ),
      SpecialAbility(
        "Hooked",
        "Your gang members use your product. Add the savage, unreliable, or wild flaw to your gangs to give them +1 quality."
      ),
      SpecialAbility(
        "Patron",
        "When you advance your Tier, it costs half the coin it normally would. Who is your patron? Why do they help you?"
      )
    ),
    CrewName.Smugglers -> List[SpecialAbility](
      SpecialAbility(
        "Like Part of the Family",
        "Create one of your vehicles as a cohort (use the vehicle edges and flaws, below). Its quality is equal to your Tier +1."
      ),
      SpecialAbility(
        "All Hands",
        "During downtime, one of your cohorts may perform a downtime activity for the crew to acquire an asset, reduce heat, or work on a long-term project."
      ),
      SpecialAbility(
        "Ghost Passage",
        "From harsh experience or occult ritual, all crew members become immune to possession by spirits, but may choose to 'carry' a second ghost as a passenger within their body."
      ),
      SpecialAbility(
        "Just Passing Through",
        "During downtime, take -1 heat. When your heat is 4 or less, you get +1d to deceive people when you pass yourselves off as ordinary citizens."
      ),
      SpecialAbility(
        "Leverage",
        "Your crew supplies contraband for other factions. Your success is good for them. Whenever you gain rep, gain +1 rep."
      ),
      SpecialAbility(
        "Reavers",
        "When you go into conflict aboard a vehicle, you gain +1 effect for vehicle damage and speed. Your vehicle gains armor."
      ),
      SpecialAbility("Renegades", "Each PC may add +1 action rating to Finesse, Prowl, or Skirmish (up to a max rating of 3).")
    ),
    CrewName.Shadows -> List[SpecialAbility](
      SpecialAbility("Everyone Steals", "Each PC may add +1 action rating to Prowl, Finesse, or Tinker (up to a max rating of 3)."),
      SpecialAbility(
        "Ghost Echoes",
        "From weird experience or occult ritual, all crew members gain the ability to see and interact with the ghostly structures, streets, and objects within the echo of Doskvol that exists in the ghost field."
      ),
      SpecialAbility("Pack Rats", "Your lair is a jumble of stolen items. When you roll to acquire an asset, take +1d."),
      SpecialAbility(
        "Patron",
        "When you advance your Tier, it costs half the coin it normally would. Who is your patron? Why do they help you?"
      ),
      SpecialAbility("Second Story", "When you execute a clandestine infiltration, you get +1d to the engagement roll."),
      SpecialAbility(
        "Slippery",
        "When you roll entanglements, roll twice and keep the one you want. When you reduce heat on the crew, take +1d."
      ),
      SpecialAbility(
        "Synchronized",
        "When you perform a group action, you may count multiple 6s from different rolls as a critical success."
      )
    )
  ).map { case (name, abilities) =>
    name -> (abilities :+ SpecialAbility(
      name = "Veteran",
      description = "Choose a special ability from another crew.",
      pointsRequired = if (name == CrewName.Assassins) 3 else 2
    ))
  }
}
