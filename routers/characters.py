from typing import List

from fastapi import APIRouter, Depends

from api.character.character import CharacterResponse, CharacterCreate
from api.common import GenericResponse
from repositories import get_user_repository, get_character_repository
from repositories.characters_repository import CharacterRepository
from repositories.users_repository import UserRepository
from util.token import oauth2_scheme, authenticate_user

router = APIRouter()


@router.post("/", response_model=GenericResponse[CharacterResponse])
async def create_character(
        character_data: CharacterCreate,
        token: str = Depends(oauth2_scheme),
        user_repo: UserRepository = Depends(get_user_repository),
        char_repo: CharacterRepository = Depends(get_character_repository),
):
    user = authenticate_user(token, user_repo)
    character = char_repo.create_character(character_data, user.id)
    return GenericResponse[CharacterResponse](details=CharacterResponse.from_orm(character))


@router.get("/", response_model=GenericResponse[List[CharacterResponse]])
async def show_characters(
        token: str = Depends(oauth2_scheme),
        user_repo: UserRepository = Depends(get_user_repository)
):
    user = authenticate_user(token, user_repo)
    return GenericResponse[List[CharacterResponse]](details=user.characters)
