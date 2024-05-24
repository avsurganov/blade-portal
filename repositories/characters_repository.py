from uuid import UUID

from sqlalchemy.orm import Session

from api.character.character import CharacterCreate
from api.character.character_skills import Skill
from schemas.character import Character
from util.playbook_defaults import PLAYBOOK_DEFAULTS


class CharacterRepository:
    def __init__(self, db: Session):
        self.db = db

    def create_character(self, character_data: CharacterCreate, user_id: UUID) -> Character:
        playbook_defaults = PLAYBOOK_DEFAULTS.get(character_data.playbook, {})

        def apply_defaults(skill, data):
            defaults = playbook_defaults.get(skill, {})
            if not data:
                data = {}
            combined = defaults.dict() if defaults else {}
            combined.update(data)
            return combined

        insight_data = character_data.insight.dict() if hasattr(character_data, 'insight') else {}
        prowess_data = character_data.prowess.dict() if hasattr(character_data, 'prowess') else {}
        resolve_data = character_data.resolve.dict() if hasattr(character_data, 'resolve') else {}

        insight = apply_defaults(Skill.INSIGHT, insight_data)
        prowess = apply_defaults(Skill.PROWESS, prowess_data)
        resolve = apply_defaults(Skill.RESOLVE, resolve_data)

        character = Character(
            name=character_data.name,
            alias=character_data.alias,
            playbook=character_data.playbook,
            heritage=character_data.heritage,
            background=character_data.background,
            vice=character_data.vice,
            user_id=user_id,
            insight=insight,
            prowess=prowess,
            resolve=resolve
        )

        self.db.add(character)
        self.db.commit()
        self.db.refresh(character)

        return character
