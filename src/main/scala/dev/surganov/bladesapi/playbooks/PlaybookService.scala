package dev.surganov.bladesapi.playbooks

import dev.surganov.bladesapi.common.ErrorResponse
import dev.surganov.bladesapi.data.playbooks.PlaybooksData
import dev.surganov.bladesapi.playbooks.models.{PlaybookListResponse, PlaybookName, PlaybookResponse}
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

@Path("/api/playbooks")
@Tag(name = "Playbooks", description = "Operations related to Playbooks")
class PlaybookService extends JsonSupport with LoggerAccess {
  def routes: Route = getAllPlaybooks ~ getPlaybookByName

  @GET
  @Path("/")
  @Operation(
    summary = "Get all playbooks",
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "All playbooks retrieved",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[PlaybookListResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    )
  )
  def getAllPlaybooks: Route = path("playbooks") {
    get {
      log.info("Retrieving all playbooks")
      complete(PlaybookListResponse(PlaybooksData.all))
    }
  }

  @GET
  @Path("{name}")
  @Operation(
    summary = "Get playbook by name",
    parameters = Array(
      new Parameter(
        name = "name",
        in = ParameterIn.PATH,
        description = "Name of the playbook",
        required = true
      )
    ),
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "Playbook retrieved",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[PlaybookResponse])))
      ),
      new ApiResponse(
        responseCode = "400",
        description = "Playbook doesn't exist",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(mediaType = "application/json", schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    )
  )
  def getPlaybookByName: Route = path("playbooks" / Segment) { name =>
    get {
      log.info(s"Retrieving playbook: $name...")
      Try { PlaybookName.withName(name) } match {
        case Success(name) => complete(PlaybookResponse(PlaybooksData.playbook(name)))
        case Failure(ex) =>
          log.error(ex.getMessage)
          complete((StatusCodes.BadRequest, ErrorResponse(message = s"Playbook [$name] doesn't exist")))
      }
    }
  }
}
