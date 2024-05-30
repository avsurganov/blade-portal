package dev.surganov.bladesapi.cohorts

import dev.surganov.bladesapi.cohorts.models.{CohortListResponse, CohortResponse, CohortType}
import dev.surganov.bladesapi.common.ErrorResponse
import dev.surganov.bladesapi.data.cohorts.CohortData
import dev.surganov.bladesapi.util.{JsonSupport, LoggerAccess}
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.{Operation, Parameter}
import jakarta.ws.rs.{GET, Path}
import org.apache.pekko.http.scaladsl.model.StatusCodes
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server._

import scala.util.{Failure, Success, Try}

@Path("/api/cohorts")
@Tag(name = "Cohorts", description = "Operations related to Cohorts")
object CohortService extends JsonSupport with LoggerAccess {
  def routes: Route = getAllCohorts ~ getCohortByName

  @GET
  @Path("/")
  @Operation(
    summary = "Get all cohorts",
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "All cohorts retrieved",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[CohortListResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    )
  )
  def getAllCohorts: Route = path("cohorts") {
    get {
      log.info("Retrieving all cohorts")
      complete(CohortListResponse(CohortData.all))
    }
  }

  @GET
  @Path("{type}")
  @Operation(
    summary = "Get cohort by name",
    parameters = Array(
      new Parameter(
        name = "name",
        in = ParameterIn.PATH,
        description = "Name of the cohort",
        required = true
      )
    ),
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "Cohort retrieved",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[CohortResponse])))
      ),
      new ApiResponse(
        responseCode = "400",
        description = "Cohort doesn't exist",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    )
  )
  def getCohortByName: Route = path("cohorts" / Segment) { _type =>
    get {
      log.info(s"Retrieving cohort: ${_type}...")
      Try { CohortType.withName(_type) } match {
        case Success(name) => complete(CohortResponse(CohortData.cohort(name)))
        case Failure(ex) =>
          log.error(ex.getMessage)
          complete((StatusCodes.BadRequest, ErrorResponse(message = s"Cohort [${_type}] doesn't exist")))
      }
    }
  }
}
