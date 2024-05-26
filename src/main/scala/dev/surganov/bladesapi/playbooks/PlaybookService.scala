package dev.surganov.bladesapi.playbooks

import dev.surganov.bladesapi.common.ErrorResponse
import dev.surganov.bladesapi.data.Playbooks
import dev.surganov.bladesapi.playbooks.models.{Playbook, PlaybookListResponse, PlaybookName, PlaybookResponse}
import dev.surganov.bladesapi.util.{JsonContentTypeSupport, JsonSupport, LoggerAccess}
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.{Operation, Parameter}
import jakarta.ws.rs.{GET, Path}
import org.apache.pekko.http.scaladsl.model.{StatusCode, StatusCodes}
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server._

import scala.util.{Failure, Success, Try}

@Path("/api/playbooks")
@Tag(name = "Playbooks", description = "Operations related to Playbooks")
object PlaybookService extends JsonSupport with JsonContentTypeSupport with LoggerAccess {
  def routes: Route = getAllPlaybooks ~ getPlaybookByName

  @GET
  @Operation(
    summary = "Get all playbooks",
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "All playbooks retrieved",
        headers = Array(new Header(name = "Content-Type", description = "application/json")),
        content = Array(new Content(schema = new Schema(implementation = classOf[PlaybookListResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    )
  )
  def getAllPlaybooks: Route = json {
    path("playbooks") {
      get {
        log.info("Retrieving all playbooks")
        complete(PlaybookListResponse(Playbooks.all))
      }
    }
  }

  @GET
  @Path("{name}")
  @Operation(
    summary = "Get playbook by name",
    parameters = Array(new Parameter(name = "name", in = ParameterIn.PATH, description = "Name of the playbook")),
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "Playbook retrieved",
        headers = Array(new Header(name = "Content-Type", description = "application/json")),
        content = Array(new Content(schema = new Schema(implementation = classOf[PlaybookResponse])))
      ),
      new ApiResponse(
        responseCode = "400",
        description = "Playbook doesn't exist",
        content = Array(new Content(schema = new Schema(implementation = classOf[ErrorResponse])))
      ),
      new ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = Array(new Content(schema = new Schema(implementation = classOf[ErrorResponse])))
      )
    )
  )
  def getPlaybookByName: Route = json {
    path("playbooks" / Segment) { name =>
      get {
        log.info(s"Retrieving playbook: $name...")

        Try { PlaybookName.withName(name) } match {
          case Success(pname) => complete(Playbook(pname, Playbooks.specialAbilities(pname)))
          case Failure(ex) =>
            log.error(ex.getMessage)
            complete((StatusCodes.BadRequest, ErrorResponse(message = s"Playbook [$name] doesn't exist")))
        }
      }
    }
  }
}
