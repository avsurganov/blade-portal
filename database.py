from databases import Database
from fastapi import Depends
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, Session

from config import settings
from repositories.campaigns_repository import CampaignRepository
from repositories.users_repository import UserRepository

DATABASE_URL = settings.DATABASE_URL

database = Database(DATABASE_URL)

# This will be used to create the tables
Base = declarative_base()

# This is a synchronous engine required by SQLAlchemy to create the tables
engine = create_engine(DATABASE_URL.replace("+asyncpg", ""), echo=True)

SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)


# Dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


# Repositories
def get_user_repository(db: Session = Depends(get_db)) -> UserRepository:
    return UserRepository(db)


def get_campaign_repository(db: Session = Depends(get_db)) -> CampaignRepository:
    return CampaignRepository(db)
