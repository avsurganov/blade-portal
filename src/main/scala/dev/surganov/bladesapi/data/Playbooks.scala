package dev.surganov.bladesapi.data

import dev.surganov.bladesapi.playbooks.models.{Playbook, PlaybookList, PlaybookName, SpecialAbility}

object Playbooks {
  private val specialAbilities: Map[PlaybookName, List[SpecialAbility]] = Map[PlaybookName, List[SpecialAbility]](
    PlaybookName.Cutter -> List[SpecialAbility](
      SpecialAbility(
        name = "Battleborn",
        description = "You may expend your special armor to reduce harm from an attack in combat or to push yourself during a fight."
      ),
      SpecialAbility(
        name = "Bodyguard",
        description = "When you protect a teammate, you gain +1d to your resistance roll."
      ),
      SpecialAbility(
        name = "Leader",
        description =
          "When you Command a cohort in combat, they continue to fight when they would otherwise break (they're not taken out when they suffer level 3 harm)."
      ),
      SpecialAbility(
        name = "Mule",
        description = "Your load limits are higher. Light: 5. Normal: 7. Heavy: 10."
      ),
      SpecialAbility(
        name = "Not to be Trifled With",
        description =
          "You can push yourself to do one of the following: perform a feat of physical force that verges on the superhuman, engage a small gang on equal footing in close combat."
      ),
      SpecialAbility(
        name = "Savage",
        description = "When you unleash physical violence, it's especially frightening. When you Command a frightened target, take +1d."
      ),
      SpecialAbility(
        name = "Vigorous",
        description = "You recover from harm faster. Permanently fill in one of your healing clock segments. +1d to healing rolls."
      ),
      SpecialAbility(
        name = "Ghost Fighter",
        description =
          "You may imbue your hands, melee weapons, or tools with spirit energy, allowing them to strike supernatural targets. You may grapple with spirits to restrain and capture them."
      )
    ),
    PlaybookName.Hound -> List[SpecialAbility](
      SpecialAbility(
        name = "Sharpshooter",
        description =
          "You can push yourself to do one of the following: make a ranged attack at extreme distance beyond what's normal for the weapon; unleash a barrage of rapid fire to suppress the enemy."
      ),
      SpecialAbility(
        name = "Focused",
        description =
          "You may expend your special armor to resist a consequence from surprise or mental harm (fear, confusion, losing track of someone) or to push yourself for ranged combat or tracking."
      ),
      SpecialAbility(
        name = "Ghost Hunter",
        description =
          "Your hunting pet is imbued with spirit energy. It gains potency when tracking or fighting the supernatural and gains an arcane ability: ghost form, mind link, or spectral claws.",
        pointsRequired = 2
      ),
      SpecialAbility(
        name = "Scout",
        description =
          "When you gather information to discover the location of a target, you get +1 effect. When you hide in a prepared position or use camouflage, you get +1d to rolls to avoid detection."
      ),
      SpecialAbility(
        name = "Survivor",
        description =
          "From hard won experience or a rigorous training regimen, you have an iron will. You gain +1d to resistance rolls with Resolve."
      ),
      SpecialAbility(
        name = "Tough as Nails",
        description = "Penalties from harm are one level less severe (though level 4 harm is still fatal)."
      ),
      SpecialAbility(
        name = "Vengeful",
        description =
          "You gain an additional XP trigger: You got payback against someone who harmed you or yours. If your crew helped you get payback, also mark crew XP."
      )
    ),
    PlaybookName.Leech -> List[SpecialAbility](
      SpecialAbility(
        name = "Alchemist",
        description =
          "When you invent or craft a creation with alchemical features, take +1 result level to your roll. You begin with one special formula already known."
      ),
      SpecialAbility(
        name = "Analyst",
        description =
          "During downtime, you get two ticks to distribute among any long term project clocks that involve investigation or learning a new formula or design plan."
      ),
      SpecialAbility(
        name = "Artificer",
        description =
          "When you invent or craft a creation with spark-craft features, take +1 result level to your roll. You begin with one special design already known."
      ),
      SpecialAbility(
        name = "Fortitude",
        description =
          "You may expend your special armor to resist a consequence of fatigue, weakness, or chemical effects, or to push yourself when working with technical skill or handling alchemicals."
      ),
      SpecialAbility(
        name = "Ghost Ward",
        description =
          "You know how to Wreck an area with arcane substances and methods so it is either anathema or enticing to spirits (your choice)."
      ),
      SpecialAbility(
        name = "Physicker",
        description =
          "You can Tinker with bones, blood, and bodily humours to treat wounds or stabilize the dying. You may study a malady or corpse. Everyone in your crew gets +1d to their healing treatment rolls."
      ),
      SpecialAbility(
        name = "Saboteur",
        description = "When you Wreck, the work is much quieter than it should be and the damage is hidden from casual inspection."
      ),
      SpecialAbility(
        name = "Venomous",
        description =
          "Choose a drug or poison (from your bandolier stock) to which you have become immune. You can push yourself to secrete it through your skin or saliva or exhale it as a vapor."
      )
    ),
    PlaybookName.Lurk -> List[SpecialAbility](
      SpecialAbility(
        name = "Infiltrator",
        description = "You are not affected by quality or Tier when you bypass security measures."
      ),
      SpecialAbility(
        name = "Ambush",
        description = "When you attack from hiding or spring a trap, you get +1d."
      ),
      SpecialAbility(
        name = "Daredevil",
        description =
          "When you roll a desperate action, you get +1d to your roll if you also take -1d to any resistance rolls against consequences from your action."
      ),
      SpecialAbility(
        name = "The Devil's Footsteps",
        description =
          "When you push yourself, choose one of the following additional benefits: perform a feat of athletics that verges on the superhuman—maneuver to confuse your enemies so they mistakenly attack each other."
      ),
      SpecialAbility(
        name = "Expertise",
        description =
          "Choose one of your action ratings. When you lead a group action using that action, you can suffer only 1 stress at most regardless of the number of failed rolls."
      ),
      SpecialAbility(
        name = "Ghost Veil",
        description =
          "You may shift partially into the ghost field, becoming shadowy and insubstantial for a few moments. Take 2 stress when you shift, plus 1 stress for each extra feature: It lasts for a few minutes rather than moments—you are invisible rather than shadowy—you may float through the air like a ghost."
      ),
      SpecialAbility(
        name = "Reflexes",
        description = "When there's a question about who acts first, the answer is you (two characters with Reflexes act simultaneously)."
      ),
      SpecialAbility(
        name = "Shadow",
        description =
          "You may expend your special armor to resist a consequence from detection or security measures, or to push yourself for a feat of athletics or stealth."
      )
    ),
    PlaybookName.Slide -> List[SpecialAbility](
      SpecialAbility(
        name = "Rook's Gambit",
        description =
          "Take 2 stress to roll your best action rating while performing a different action. Say how you adapt your skill to this use."
      ),
      SpecialAbility(
        name = "Cloak & Dagger",
        description =
          "When you use a disguise or other form of covert misdirection, you get +1d to rolls to confuse or deflect suspicion. When you throw off your disguise, the resulting surprise gives you the initiative in the situation."
      ),
      SpecialAbility(
        name = "Ghost Voice",
        description =
          "You know the secret method to interact with a ghost or demon as if it was a normal human, regardless of how wild or feral it appears. You gain potency when communicating with the supernatural."
      ),
      SpecialAbility(
        name = "Like Looking into a Mirror",
        description = "You can always tell when someone is lying to you."
      ),
      SpecialAbility(
        name = "A Little Something on the Side",
        description = "At the end of each downtime phase, you earn +2 stash."
      ),
      SpecialAbility(
        name = "Mesmerism",
        description = "When you Sway someone, you may cause them to forget that it's happened until they next interact with you."
      ),
      SpecialAbility(
        name = "Subterfuge",
        description =
          "You may expend your special armor to resist a consequence from suspicion or persuasion, or to push yourself for subterfuge."
      ),
      SpecialAbility(
        name = "Trust in Me",
        description = "You get +1d vs. a target with whom you have an intimate relationship."
      )
    ),
    PlaybookName.Spider -> List[SpecialAbility](
      SpecialAbility(
        name = "Foresight",
        description = "Two times per score you can assist a teammate without paying stress. Tell us how you prepared for this."
      ),
      SpecialAbility(
        name = "Calculating",
        description = "Due to your careful planning, during downtime, you may give yourself or another crew member +1 downtime action."
      ),
      SpecialAbility(
        name = "Connected",
        description = "During downtime, you get +1 result level when you acquire an asset or reduce heat."
      ),
      SpecialAbility(
        name = "Functioning Vice",
        description =
          "When you indulge your vice, you may adjust the dice outcome by 1 or 2 (up or down). An ally who joins in your vice may do the same."
      ),
      SpecialAbility(
        name = "Ghost Contract",
        description =
          "When you shake on a deal, you and your partner—human or otherwise—both bear a mark of your oath. If either breaks the contract, they take level 3 harm, 'Cursed'."
      ),
      SpecialAbility(
        name = "Jail Bird",
        description =
          "When incarcerated, your wanted level counts as 1 less, your Tier as 1 more, and you gain +1 faction status with a faction you help on the inside (in addition to your incarceration roll)."
      ),
      SpecialAbility(
        name = "Mastermind",
        description =
          "You may expend your special armor to protect a teammate, or to push yourself when you gather information or work on a long-term project."
      ),
      SpecialAbility(
        name = "Weaving the Web",
        description =
          "You gain +1d to Consort when you gather information on a target for a score. You get +1d to the engagement roll for that operation."
      )
    ),
    PlaybookName.Whisper -> List[SpecialAbility](
      SpecialAbility(
        name = "Compel",
        description =
          "You can Attune to the ghost field to force a nearby ghost to appear and obey a command you give it. You are not supernaturally terrified by a ghost you summon or compel (though your allies may be)."
      ),
      SpecialAbility(
        name = "Ghost Mind",
        description = "You’re always aware of supernatural entities in your presence. Take +1d when you gather info about the supernatural."
      ),
      SpecialAbility(
        name = "Iron Will",
        description =
          "You're immune to the terror that some supernatural entities inflict on sight. Take +1d to resistance rolls with Resolve."
      ),
      SpecialAbility(
        name = "Occultist",
        description =
          "You know the secret ways to Consort with ancient powers, forgotten gods or demons. Once you've consorted with one, you get +1d to command cultists who worship it."
      ),
      SpecialAbility(
        name = "Ritual",
        description =
          "You can Study an occult ritual (or create a new one) to summon a supernatural effect or being. You know the arcane methods to perform ritual sorcery. You begin with one ritual already learned."
      ),
      SpecialAbility(
        name = "Strange Methods",
        description =
          "When you invent or craft a creation with arcane features, take +1 result level to your roll. You begin with one arcane design already known."
      ),
      SpecialAbility(
        name = "Tempest",
        description =
          "You can push yourself to do one of the following: unleash a stroke of lightning as a weapon—summon a storm in your immediate vicinity (torrential rain, roaring winds, heavy fog, chilling frost/snow, etc.)."
      ),
      SpecialAbility(
        name = "Warded",
        description =
          "You may expend your special armor to resist a supernatural consequence, or to push yourself when you deal with arcane forces."
      )
    )
  ).map { case (name, abilities) =>
    name -> (abilities :+ SpecialAbility(
      name = "Veteran",
      description = "Choose a special ability from another source.",
      pointsRequired = 3
    ))
  }

  def all: PlaybookList = PlaybookList(playbooks = {
    PlaybookName.all.map { name =>
      Playbook(name, specialAbilities(name))
    }
  })
}
