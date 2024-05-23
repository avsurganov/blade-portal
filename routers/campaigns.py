from fastapi import APIRouter, Depends, status, HTTPException

from api.campaigns import CampaignResponse, CampaignCreate, JoinCampaignRequest
from api.common import GenericResponse
from database import get_user_repository, get_campaign_repository
from schemas.campaigns import Campaign
from repositories.campaigns_repository import CampaignRepository
from repositories.users_repository import UserRepository
from util.token import oauth2_scheme, authenticate_user

router = APIRouter()


@router.post("/", response_model=GenericResponse[CampaignResponse])
async def create_campaign(
    campaign: CampaignCreate,
    token: str = Depends(oauth2_scheme),
    user_repo: UserRepository = Depends(get_user_repository),
    campaign_repo: CampaignRepository = Depends(get_campaign_repository),
):
    authenticate_user(token, user_repo)

    # Check if game_master_id exists in the users table
    game_master = user_repo.get_user_by_id(campaign.game_master_id)
    if not game_master:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND, detail="Game master not found"
        )

    db_campaign = Campaign(name=campaign.name, game_master_id=campaign.game_master_id)
    db_campaign = campaign_repo.save(db_campaign)
    return GenericResponse[CampaignResponse](
        details=CampaignResponse.from_orm(db_campaign)
    )


@router.post("/join", response_model=GenericResponse[str])
async def join_campaign(
    join_request: JoinCampaignRequest,
    token: str = Depends(oauth2_scheme),
    user_repo: UserRepository = Depends(get_user_repository),
    campaign_repo: CampaignRepository = Depends(get_campaign_repository),
):
    authenticate_user(token, user_repo)

    # Check if campaign exists
    campaign = campaign_repo.get_campaign_by_id(join_request.campaign_id)
    if not campaign:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND, detail="Campaign not found"
        )

    # Check if user exists
    user = user_repo.get_user_by_id(join_request.user_id)
    if not user:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND, detail="User not found"
        )

    # Add user to campaign's players
    if user in campaign.players:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT,
            detail="User already joined this campaign",
        )

    campaign_repo.add_player_to_campaign(campaign, user)

    return GenericResponse[str](details="User successfully joined the campaign")
