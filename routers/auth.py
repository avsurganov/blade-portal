from datetime import timedelta
from typing import Dict

from fastapi import APIRouter, HTTPException, status
from fastapi import Depends
from sqlalchemy.orm import Session

from api.auth import UserLogin
from api.common import GenericResponse
from database import get_db
from schemas.users import get_user_by_email
from util.hashing import verify_password
from util.token import create_access_token, ACCESS_TOKEN_EXPIRE_MINUTES

router = APIRouter()


@router.post("/login/", response_model=GenericResponse)
def login(user: UserLogin, db: Session = Depends(get_db)):
    db_user = get_user_by_email(db, user.email)
    if not db_user or not verify_password(user.password, db_user.hashed_password):
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Could not validate credentials",
            headers={"WWW-Authenticate": "Bearer"},
        )
    access_token_expires = timedelta(minutes=ACCESS_TOKEN_EXPIRE_MINUTES)
    access_token = create_access_token(
        data={"sub": db_user.email}, expires_delta=access_token_expires
    )
    return GenericResponse[Dict](
        details={
            "user_id": db_user.id,
            "access_token": access_token,
            "token_type": "bearer",
        }
    )
