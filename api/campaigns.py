from typing import List
from uuid import UUID

from pydantic import BaseModel

from api.users import UserResponse


class CampaignCreate(BaseModel):
    name: str
    game_master_id: UUID


class CampaignResponse(BaseModel):
    id: UUID
    name: str
    game_master: UserResponse
    players: List[UserResponse]

    class Config:
        from_attributes = True


class JoinCampaignRequest(BaseModel):
    user_id: UUID
    campaign_id: UUID
