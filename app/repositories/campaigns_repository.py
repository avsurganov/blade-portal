from sqlalchemy.orm import Session
from app.schemas.campaign import Campaign
from uuid import UUID

from app.schemas.user import User


class CampaignRepository:
    def __init__(self, db: Session):
        self.db = db

    def get_campaign_by_id(self, campaign_id: UUID) -> Campaign:
        return self.db.query(Campaign).filter(Campaign.id == campaign_id).first()

    def add_player_to_campaign(self, campaign: Campaign, user: User) -> None:
        campaign.players.append(user)
        self.db.commit()

    def save(self, campaign: Campaign) -> Campaign:
        self.db.add(campaign)
        self.db.commit()
        self.db.refresh(campaign)
        return campaign
