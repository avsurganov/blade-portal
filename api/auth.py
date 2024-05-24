from uuid import UUID

from pydantic import BaseModel


class UserLogin(BaseModel):
    email: str
    password: str


class AuthResponse(BaseModel):
    user_id: UUID
    access_token: str
    token_type: str
