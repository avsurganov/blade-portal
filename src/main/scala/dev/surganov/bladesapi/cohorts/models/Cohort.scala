package dev.surganov.bladesapi.cohorts.models

import dev.surganov.bladesapi.common.{SuccessResponse, SuccessResponseFormat}
import play.api.libs.json.{Format, Json}

case class Cohort(
    cohortType: CohortType,
    edges: List[Edge],
    flaws: List[Flaw],
    harmPoints: Int = 4
)
object Cohort { implicit val format: Format[Cohort] = Json.format[Cohort] }

case class CohortList(cohorts: List[Cohort])
object CohortList { implicit val format: Format[CohortList] = Json.format[CohortList] }

case class CohortResponse(override val details: Cohort) extends SuccessResponse[Cohort](details = details)
object CohortResponse { implicit val format: Format[CohortResponse] = SuccessResponseFormat.format(CohortResponse.apply) }

case class CohortListResponse(override val details: CohortList) extends SuccessResponse[CohortList](details = details)
object CohortListResponse { implicit val format: Format[CohortListResponse] = SuccessResponseFormat.format(CohortListResponse.apply) }
