from typing import List

from fastapi import APIRouter, Depends, status, HTTPException
from sqlalchemy.exc import IntegrityError

from api.common import GenericResponse
from api.users import UserCreate, UserResponse
from database import get_user_repository
from schemas.users import User
from repositories.users_repository import UserRepository
from util.hashing import hash_password
from util.token import oauth2_scheme, authenticate_user

router = APIRouter()


@router.post("/", response_model=GenericResponse[UserResponse])
async def create_user(user: UserCreate, user_repo: UserRepository = Depends(get_user_repository)):
    try:
        db_user = User(
            email=user.email,
            first_name=user.first_name,
            last_name=user.last_name,
            avatar=user.avatar,
            hashed_password=hash_password(
                user.password
            ),  # You should hash the password before storing it
        )
        db_user = user_repo.save(db_user)
        return GenericResponse[UserResponse](details=UserResponse.from_orm(db_user))
    except IntegrityError:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT, detail="User already exists"
        )


@router.get("/", response_model=GenericResponse[List[UserResponse]])
async def read_users(
    token: str = Depends(oauth2_scheme),
    user_repo: UserRepository = Depends(get_user_repository)
):
    authenticate_user(token, user_repo)
    users = user_repo.get_all_users()
    return GenericResponse[List[UserResponse]](details=users)
