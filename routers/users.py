from typing import List

from fastapi import APIRouter, Depends, status, HTTPException
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm import Session

from api.common import GenericResponse
from api.users import UserCreate, UserResponse
from database import get_db, save_to_db
from models.users import User
from util.hashing import hash_password
from util.token import oauth2_scheme, authenticate_user

router = APIRouter()


@router.post("/", response_model=GenericResponse[UserResponse])
async def create_user(user: UserCreate, db: Session = Depends(get_db)):
    try:
        db_user = User(
            email=user.email,
            first_name=user.first_name,
            last_name=user.last_name,
            avatar=user.avatar,
            hashed_password=hash_password(user.password)  # You should hash the password before storing it
        )
        db_user = save_to_db(db, db_user)
        return GenericResponse[UserResponse](details=UserResponse.from_orm(db_user))
    except IntegrityError:
        raise HTTPException(status_code=status.HTTP_409_CONFLICT, detail="User already exists")


@router.get("/", response_model=GenericResponse[List[UserResponse]])
async def read_users(skip: int = 0, limit: int = 10, token: str = Depends(oauth2_scheme), db: Session = Depends(get_db)):
    authenticate_user(token, db)
    users = db.query(User).offset(skip).limit(limit).all()
    return GenericResponse[List[UserResponse]](details=users)
