package dev.surganov.bladesapi.crews.models

import dev.surganov.bladesapi.common.models.{Contact, SpecialAbility}
import dev.surganov.bladesapi.common.{SuccessResponse, SuccessResponseFormat}
import play.api.libs.json.{Format, Json}

case class Crew(
    name: CrewName,
    specialAbilities: List[SpecialAbility],
    contacts: List[Contact]
)
object Crew { implicit val format: Format[Crew] = Json.format[Crew] }

case class CrewList(crews: List[Crew])
object CrewList { implicit val format: Format[CrewList] = Json.format[CrewList] }

case class CrewResponse(override val details: Crew) extends SuccessResponse[Crew](details = details)
object CrewResponse { implicit val format: Format[CrewResponse] = SuccessResponseFormat.format(CrewResponse.apply) }

case class CrewListResponse(override val details: CrewList) extends SuccessResponse[CrewList](details = details)
object CrewListResponse { implicit val format: Format[CrewListResponse] = SuccessResponseFormat.format(CrewListResponse.apply) }
