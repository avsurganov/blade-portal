from datetime import timedelta

from fastapi import APIRouter
from fastapi import Depends
from sqlalchemy.orm import Session

from api.auth import UserLogin
from api.common import GenericResponse
from database import get_db
from util.hashing import verify_password
from util.token import create_access_token, ACCESS_TOKEN_EXPIRE_MINUTES, get_user

router = APIRouter()


@router.post("/login/", response_model=GenericResponse)
def login(user: UserLogin, db: Session = Depends(get_db)):
    db_user = get_user(db, user.username)
    if not db_user or not verify_password(user.password, db_user.hashed_password):
        return GenericResponse(status="Error", details="Invalid credentials")

    access_token_expires = timedelta(minutes=ACCESS_TOKEN_EXPIRE_MINUTES)
    access_token = create_access_token(data={"sub": db_user.username}, expires_delta=access_token_expires)
    return GenericResponse(status="Success", details={"access_token": access_token, "token_type": "bearer"})




