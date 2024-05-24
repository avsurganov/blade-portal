from sqlalchemy import Column, String, ForeignKey, Integer, CheckConstraint, Enum
from sqlalchemy.dialects.postgresql import UUID, JSONB
from sqlalchemy.orm import relationship
import uuid

from app.api.character.character_attributes import Playbook, Heritage, Background, Vice
from app.database import Base


class Character(Base):
    __tablename__ = "characters"

    id = Column(
        UUID(as_uuid=True),
        primary_key=True,
        default=uuid.uuid4,
        unique=True,
        index=True,
        nullable=False,
    )
    name = Column(String, index=True, nullable=False)
    alias = Column(String, nullable=True)
    playbook = Column(Enum(Playbook), index=True, nullable=False)
    heritage = Column(Enum(Heritage), index=True, nullable=False)
    background = Column(Enum(Background), index=True, nullable=False)
    vice = Column(Enum(Vice), index=True, nullable=False)
    stress = Column(
        Integer,
        CheckConstraint("stress >= 0 AND stress <= 10", name="check_stress"),
        nullable=False,
        default=0,
    )
    trauma = Column(
        Integer,
        CheckConstraint("trauma >= 0 AND trauma <= 4", name="check_trauma"),
        nullable=False,
        default=0,
    )
    user_id = Column(UUID(as_uuid=True), ForeignKey("users.id"), nullable=False)

    insight = Column(JSONB, nullable=False, default=dict)
    prowess = Column(JSONB, nullable=False, default=dict)
    resolve = Column(JSONB, nullable=False, default=dict)

    user = relationship("User", back_populates="characters")
