from typing import Optional
from uuid import UUID

from pydantic import BaseModel

from api.character.character_attributes import Playbook, Heritage, Background, Vice
from api.character.character_skills import Insight, Prowess, Resolve


class CharacterCreate(BaseModel):
    name: str
    alias: Optional[str] = None
    playbook: Playbook
    heritage: Heritage
    background: Background
    vice: Vice


class CharacterResponse(BaseModel):
    id: UUID
    name: str
    alias: Optional[str]
    playbook: str
    heritage: str
    background: str
    vice: str
    stress: int
    trauma: int
    insight: Insight
    prowess: Prowess
    resolve: Resolve

    class Config:
        from_attributes = True
