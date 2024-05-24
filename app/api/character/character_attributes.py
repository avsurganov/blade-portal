from enum import Enum


class Playbook(Enum):
    WHISPER = "Whisper"
    HOUND = "Hound"
    LEECH = "Leech"
    LURK = "Lurk"
    SLIDE = "Slide"
    SPIDER = "Spider"
    CUTTER = "Cutter"


class Heritage(Enum):
    AKOROS = "Akoros"
    DAGGER_ISLES = "Dagger Isles"
    IRUVIAN = "Iruvian"
    SKOVLAN = "Skovlan"
    SEVEROS = "Severos"
    TYCHEROS = "Tycheros"


class Background(Enum):
    ACADEMIC = "Academic"
    LABOR = "Labor"
    LAW = "Law"
    TRADE = "Trade"
    MILITARY = "Military"
    NOBLE = "Noble"
    UNDERWORLD = "Underworld"


class Vice(Enum):
    FAITH = "Faith"
    GAMBLING = "Gambling"
    LUXURY = "Luxury"
    OBLIGATION = "Obligation"
    PLEASURE = "Pleasure"
    STUPOR = "Stupor"
    WEIRD = "Weird"
