from databases import Database
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, Session

from config import settings

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


# Utility
def save_to_db(db: Session, db_model):
    db.add(db_model)
    db.commit()
    db.refresh(db_model)
    return db_model
