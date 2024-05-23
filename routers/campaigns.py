from fastapi import APIRouter, Depends, status, HTTPException
from sqlalchemy.orm import Session

from api.campaigns import CampaignResponse, CampaignCreate, JoinCampaignRequest
from api.common import GenericResponse
from database import get_db, save_to_db
from models.campaigns import Campaign, get_campaign_by_id
from models.users import get_user_by_id
from util.token import oauth2_scheme, authenticate_user

router = APIRouter()


@router.post("/", response_model=GenericResponse[CampaignResponse])
async def create_campaign(campaign: CampaignCreate, token: str = Depends(oauth2_scheme), db: Session = Depends(get_db)):
    authenticate_user(token, db)

    # Check if game_master_id exists in the users table
    game_master = get_user_by_id(db, campaign.game_master_id)
    if not game_master:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Game master not found")

    db_campaign = Campaign(
        name=campaign.name,
        game_master_id=campaign.game_master_id
    )
    db_campaign = save_to_db(db, db_campaign)
    return GenericResponse[CampaignResponse](details=CampaignResponse.from_orm(db_campaign))


@router.post("/join", response_model=GenericResponse[str])
async def join_campaign(join_request: JoinCampaignRequest, token: str = Depends(oauth2_scheme), db: Session = Depends(get_db)):
    authenticate_user(token, db)

    # Check if campaign exists
    campaign = get_campaign_by_id(db, join_request.campaign_id)
    if not campaign:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Campaign not found")

    # Check if user exists
    user = get_user_by_id(db, join_request.user_id)
    if not user:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="User not found")

    # Add user to campaign's players
    if user in campaign.players:
        raise HTTPException(status_code=status.HTTP_409_CONFLICT, detail="User already joined this campaign")

    campaign.players.append(user)
    db.commit()

    return GenericResponse[str](details="User successfully joined the campaign")