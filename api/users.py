import uuid
from pydantic import BaseModel


class UserCreate(BaseModel):
    username: str
    email: str
    first_name: str
    last_name: str
    avatar: str = None  # Optional field
    password: str


class UserRead(BaseModel):
    id: uuid.UUID
    username: str
    email: str
    first_name: str
    last_name: str
    avatar: str = None

    class Config:
        orm_mode = True
