import uuid

from sqlalchemy import Column, String, ForeignKey
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.orm import relationship

from database import Base
from schemas.user_campaign import user_campaign


class Campaign(Base):
    __tablename__ = "campaigns"

    id = Column(UUID(as_uuid=True), primary_key=True, default=uuid.uuid4, index=True)
    name = Column(String, index=True)
    game_master_id = Column(UUID(as_uuid=True), ForeignKey("users.id"))

    game_master = relationship("User", back_populates="campaigns")
    players = relationship(
        "User", secondary=user_campaign, back_populates="joined_campaigns"
    )

    class Config:
        from_attributes = True
