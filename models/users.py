import uuid

from sqlalchemy import Column, String
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.orm import relationship, Session

from database import Base
from models.user_campaign import user_campaign


class User(Base):
    __tablename__ = "users"

    id = Column(UUID(as_uuid=True), primary_key=True, default=uuid.uuid4, unique=True, index=True, nullable=False)
    email = Column(String, unique=True, index=True)
    first_name = Column(String, index=True)
    last_name = Column(String, index=True)
    avatar = Column(String, nullable=True)
    hashed_password = Column(String)

    campaigns = relationship("Campaign", back_populates="game_master")
    joined_campaigns = relationship("Campaign", secondary=user_campaign, back_populates="players")


# Utility
def get_user_by_email(db: Session, email: str):
    return db.query(User).filter(User.email == email).first()


def get_user_by_id(db: Session, user_id: UUID):
    return db.query(User).filter(User.id == user_id).first()
