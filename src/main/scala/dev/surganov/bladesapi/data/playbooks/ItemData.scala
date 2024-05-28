package dev.surganov.bladesapi.data.playbooks

import dev.surganov.bladesapi.playbooks.models.{Item, PlaybookName}

object ItemData {
  val data: Map[PlaybookName, List[Item]] = Map[PlaybookName, List[Item]](
    PlaybookName.Cutter -> List[Item](
      Item("Fine hand weapon", "A finely crafted one-handed melee weapon of your choice, perfectly balanced for skilled combatants."),
      Item("Fine heavy weapon", "A well-made two-handed melee weapon, delivering powerful strikes with precision and reach.", 2),
      Item(
        "Scary weapon or tool",
        "An intimidating weapon or tool designed to inspire fear in adversaries, increasing your effect when intimidating."
      ),
      Item("Manacles & chain", "A set of sturdy manacles and chain, ideal for securely restraining captives.", 0),
      Item(
        "Rage essence vial",
        "A single dose that greatly enhances the user’s strength, resistance to pain, and induces irrational aggression for several minutes.",
        0
      ),
      Item("Spiritbane charm", "A charm imbued with protective magic, warding off malevolent spirits and preventing possession.", 0)
    ),
    PlaybookName.Hound -> List[Item](
      Item(
        "Fine pair of pistols",
        "A pair of exquisitely crafted pistols, designed for accuracy and reliability in quick-draw situations."
      ),
      Item("Fine long rifle", "A high-quality long rifle, providing exceptional range and precision for long-distance targets.", 2),
      Item(
        "Electroplasmic ammunition",
        "Special ammunition infused with electroplasm, capable of harming or immobilizing spirits and other supernatural entities."
      ),
      Item(
        "A trained hunting pet",
        "A loyal and well-trained pet, skilled in tracking and assisting in hunts, often imbued with supernatural abilities.",
        0
      ),
      Item("Spyglass", "A high-quality spyglass for observing distant objects or locations with clarity.", 0),
      Item("Spiritbane charm", "A charm imbued with protective magic, warding off malevolent spirits and preventing possession.", 0)
    ),
    PlaybookName.Leech -> List[Item](
      Item("Fine tinkering tools", "A set of precision-crafted tools perfect for delicate mechanical work and intricate repairs."),
      Item(
        "Fine wrecking tools",
        "Heavy-duty, high-quality tools designed for efficient demolition and breaking through tough materials.",
        2
      ),
      Item(
        "Blowgun & darts, syringes",
        "A stealthy blowgun with various darts and syringes for delivering poisons or tranquilizers silently.",
        0
      ),
      Item(
        "Bandolier (3 uses)",
        "A belt equipped with multiple compartments to hold three different alchemical items or grenades for quick access."
      ),
      Item(
        "Bandolier (3 uses)",
        "Another belt equipped with multiple compartments to hold three different alchemical items or grenades for quick access."
      ),
      Item(
        "Gadgets",
        "A collection of small, ingenious devices and tools for various purposes, ranging from lockpicking to minor mechanical tasks.",
        3
      )
    ),
    PlaybookName.Lurk -> List[Item](
      Item("Fine lockpicks", "A set of expertly crafted lockpicks, designed for maximum precision and efficiency in bypassing locks.", 0),
      Item("Fine shadow cloak", "A high-quality cloak that blends into the shadows, providing superior concealment in dark environments."),
      Item(
        "Light climbing gear",
        "A set of lightweight gear, including ropes and grappling hooks, ideal for scaling walls and other obstacles."
      ),
      Item(
        "Silence potion vial",
        "A vial containing a potion that, when consumed, silences all sounds made by the drinker for a short period.",
        0
      ),
      Item(
        "Dark-sight goggles",
        "Goggles that allow the wearer to see clearly in complete darkness, enhancing vision in low-light conditions."
      ),
      Item("Spiritbane charm", "A charm imbued with protective magic, warding off malevolent spirits and preventing possession.", 0)
    ),
    PlaybookName.Slide -> List[Item](
      Item(
        "Fine clothes & jewelry",
        "Elegant and luxurious attire, complete with exquisite jewelry, perfect for impressing high society.",
        0
      ),
      Item("Fine disguise kit", "A comprehensive kit containing high-quality materials for creating convincing disguises."),
      Item("Fine loaded dice, trick cards", "A set of expertly crafted dice and cards designed for cheating in gambling games.", 0),
      Item(
        "Trance powder",
        "A powder that induces a trance-like state in those who inhale it, often used for interrogation or persuasion.",
        0
      ),
      Item("A cane-sword", "A stylish cane that conceals a sharp, hidden blade, suitable for both walking and self-defense."),
      Item("Spiritbane charm", "A charm imbued with protective magic, warding off malevolent spirits and preventing possession.", 0)
    ),
    PlaybookName.Spider -> List[Item](
      Item(
        "Fine cover identity",
        "A meticulously crafted false identity, complete with documentation and background story, perfect for infiltration.",
        0
      ),
      Item("Fine bottle of whiskey", "A high-quality bottle of whiskey, aged to perfection, often used to impress or bribe."),
      Item("Blueprints", "Detailed architectural plans, useful for planning heists or understanding building layouts."),
      Item("Vial of slumber essence", "A vial containing a potent liquid that induces deep sleep when ingested or inhaled.", 0),
      Item(
        "Concealed palm pistol",
        "A small, easily hidden pistol designed to be concealed in the palm of the hand for quick and discreet use.",
        0
      ),
      Item("Spiritbane charm", "A charm imbued with protective magic, warding off malevolent spirits and preventing possession.", 0)
    ),
    PlaybookName.Whisper -> List[Item](
      Item(
        "Fine lightning hook",
        "A specialized tool used for grappling and channeling electroplasmic energy, ideal for capturing and manipulating spirits.",
        2
      ),
      Item("Fine spirit mask", "An intricately designed mask that allows the wearer to see and interact with the ghost field and spirits."),
      Item("Electroplasm vials", "Vials containing electroplasm, used for powering arcane devices or combating supernatural entities.", 0),
      Item("Spirit bottles (2)", "Bottles designed to safely contain and transport spirits."),
      Item("Ghost key", "A key that can unlock ghost doors and interact with spectral locks.", 0),
      Item("Demonbane charm", "A charm imbued with powerful magic, specifically designed to ward off and protect against demons.", 0)
    )
  )

  val defaultItems: List[Item] = List[Item](
    Item("A Blade or Two", "A pair of sharp blades, perfect for close combat and quick, precise attacks."),
    Item("Throwing Knives", "A set of balanced knives designed for throwing, providing a ranged option for silent takedowns."),
    Item("A Pistol", "A reliable handgun for quick, effective ranged attacks."),
    Item("A 2nd Pistol", "An additional handgun, offering extra firepower or a backup in case of emergencies."),
    Item("A Large Weapon", "A formidable weapon that delivers heavy damage, suitable for overwhelming force.", 2),
    Item("An Unusual Weapon", "A unique or exotic weapon with special properties or effects."),
    Item("Armor", "Protective gear that provides basic defense against physical attacks.", 2),
    Item("Heavy Armor", "Robust armor offering substantial protection at the cost of mobility.", 5),
    Item("Burglary Gear", "Tools and equipment designed for breaking and entering, ideal for stealthy heists."),
    Item("Climbing Gear", "Essential equipment for scaling walls and other vertical surfaces.", 2),
    Item("Arcane Implements", "Mystical tools and items used for performing arcane rituals and spells."),
    Item("Documents", "Important papers, such as forged identities, plans, or other critical information."),
    Item("Subterfuge Supplies", "Items useful for deception and infiltration, including disguises and false credentials."),
    Item("Demolition Tools", "Specialized tools designed for controlled demolitions and breaking through structures.", 2),
    Item("Tinkering Tools", "Precision tools for delicate mechanical work and repairs."),
    Item("Lantern", "A portable light source to illuminate dark areas, essential for exploring and working in low-light conditions.")
  )
}
