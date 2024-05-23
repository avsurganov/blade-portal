from typing import List

from fastapi import APIRouter, Depends
from fastapi import HTTPException, status
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm import Session

from api.common import GenericResponse
from api.users import UserCreate, UserRead
from database import get_db
from models.users import User
from util.hashing import hash_password
from util.token import oauth2_scheme, verify_access_token, get_user

router = APIRouter()


@router.post("/users/", response_model=GenericResponse[UserRead])
async def create_user(user: UserCreate, db: Session = Depends(get_db)):
    try:
        db_user = User(
            username=user.username,
            email=user.email,
            first_name=user.first_name,
            last_name=user.last_name,
            avatar=user.avatar,
            hashed_password=hash_password(user.password)  # You should hash the password before storing it
        )
        db.add(db_user)
        db.commit()
        db.refresh(db_user)
        return GenericResponse[UserRead](status="Success", details=db_user)
    except IntegrityError:
        return GenericResponse[str](status="Error", details="User already exists")


@router.get("/users/", response_model=GenericResponse[List[UserRead]])
async def read_users(skip: int = 0, limit: int = 10, token: str = Depends(oauth2_scheme), db: Session = Depends(get_db)):
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Could not validate credentials",
        headers={"WWW-Authenticate": "Bearer"},
    )
    try:
        username = verify_access_token(token, credentials_exception)
    except HTTPException as e:
        return GenericResponse[str](status="Error", details="Unauthorized")

    user = get_user(db, username)

    if user is None:
        return GenericResponse[str](status="Error", details="User not found")

    users = db.query(User).offset(skip).limit(limit).all()

    return GenericResponse[List[UserRead]](status="Success", details=users)
