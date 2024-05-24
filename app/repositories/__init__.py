from sqlalchemy.orm import Session
from fastapi import Depends

from app.database import get_db
from app.repositories.campaigns_repository import CampaignRepository
from app.repositories.characters_repository import CharacterRepository
from app.repositories.users_repository import UserRepository


def get_user_repository(db: Session = Depends(get_db)) -> UserRepository:
    return UserRepository(db)


def get_campaign_repository(db: Session = Depends(get_db)) -> CampaignRepository:
    return CampaignRepository(db)


def get_character_repository(db: Session = Depends(get_db)) -> CharacterRepository:
    return CharacterRepository(db)