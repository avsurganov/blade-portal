from sqlalchemy import Column, ForeignKey, Table
from sqlalchemy.dialects.postgresql import UUID

from database import Base

user_campaign = Table(
    'user_campaign',
    Base.metadata,
    Column('user_id', UUID(as_uuid=True), ForeignKey('users.id'), primary_key=True),
    Column('campaign_id', UUID(as_uuid=True), ForeignKey('campaigns.id'), primary_key=True)
)

