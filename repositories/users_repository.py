from typing import Type
from uuid import UUID

from sqlalchemy.orm import Session

from schemas.user import User


class UserRepository:
    def __init__(self, db: Session):
        self.db = db

    def get_user_by_email(self, email: str) -> User:
        return self.db.query(User).filter(User.email == email).first()

    def get_user_by_id(self, user_id: UUID) -> User:
        return self.db.query(User).filter(User.id == user_id).first()

    def get_all_users(self) -> list[Type[User]]:
        return self.db.query(User).all()

    def save(self, user: User) -> User:
        self.db.add(user)
        self.db.commit()
        self.db.refresh(user)
        return user
