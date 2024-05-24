import uuid
from typing import List

from pydantic import BaseModel


class UserCreate(BaseModel):
    email: str
    first_name: str
    last_name: str
    avatar: str = None  # Optional field
    password: str


class CampaignBriefResponse(BaseModel):
    id: uuid.UUID
    name: str

    class Config:
        from_attributes = True


class UserResponse(BaseModel):
    id: uuid.UUID
    email: str
    first_name: str
    last_name: str
    avatar: str = None
    campaigns: List[CampaignBriefResponse] = []
    joined_campaigns: List[CampaignBriefResponse] = []

    class Config:
        from_attributes = True
