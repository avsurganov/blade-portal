from typing import List

from fastapi import APIRouter, Depends, status, HTTPException
from sqlalchemy.exc import IntegrityError

from api.common import GenericResponse
from api.users import UserCreate, UserResponse
from repositories import get_user_repository
from repositories.users_repository import UserRepository
from util.token import oauth2_scheme, authenticate_user

router = APIRouter()


@router.post("/", response_model=GenericResponse[UserResponse])
async def create_user(
    user: UserCreate, user_repo: UserRepository = Depends(get_user_repository)
):
    try:
        db_user = user_repo.create_user(user)
        return GenericResponse[UserResponse](details=UserResponse.from_orm(db_user))
    except IntegrityError:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT, detail="User already exists"
        )


@router.get("/", response_model=GenericResponse[List[UserResponse]])
async def show_users(
    token: str = Depends(oauth2_scheme),
    user_repo: UserRepository = Depends(get_user_repository),
):
    authenticate_user(token, user_repo)
    users = user_repo.get_all_users()
    return GenericResponse[List[UserResponse]](details=users)
