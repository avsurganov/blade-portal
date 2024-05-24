from pydantic import BaseModel
from enum import Enum


class Skill(Enum):
    INSIGHT = "Insight"
    PROWESS = "Prowess"
    RESOLVE = "Resolve"


class Insight(BaseModel):
    hunt: int = 0
    study: int = 0
    survey: int = 0
    tinker: int = 0
    points: int = 0


class Prowess(BaseModel):
    finesse: int = 0
    prowl: int = 0
    skirmish: int = 0
    wreck: int = 0
    points: int = 0


class Resolve(BaseModel):
    attune: int = 0
    command: int = 0
    consort: int = 0
    sway: int = 0
    points: int = 0
