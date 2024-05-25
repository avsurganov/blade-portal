package dev.surganov.bladesapi.playbooks

import dev.surganov.bladesapi.common.StatusResponse
import dev.surganov.bladesapi.data.Playbooks
import dev.surganov.bladesapi.playbooks.models.{Playbook, PlaybookList, PlaybookName}
import dev.surganov.bladesapi.util.{JsonContentTypeSupport, JsonSupport, LoggerAccess}
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.{Operation, Parameter}
import jakarta.ws.rs.{GET, Path}
import org.apache.pekko.http.scaladsl.model.ContentTypes
import org.apache.pekko.http.scaladsl.model.headers.`Content-Type`
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server._

@Path("/api/playbooks")
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
        content = Array(new Content(schema = new Schema(implementation = classOf[PlaybookList])))
      ),
      new ApiResponse(responseCode = "500", description = "Internal server error")
    )
  )
  def getAllPlaybooks: Route = json {
    path("playbooks") {
      get {
        respondWithHeader(`Content-Type`(ContentTypes.`application/json`)) {
          log.info("Retrieving all playbooks")
          complete(Playbooks.all)
        }
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
        content = Array(new Content(schema = new Schema(implementation = classOf[Playbook])))
      ),
      new ApiResponse(responseCode = "404", description = "Playbook not found")
    )
  )
  def getPlaybookByName: Route = json {
    path("playbooks" / Segment) { name =>
      get {
        log.info(s"Retrieving playbook: $name")
        val playbookName = PlaybookName.withName(name)
        rejectEmptyResponse {
          complete(Playbook(playbookName, Playbooks.specialAbilities(playbookName)))
        }
      }
    }
  }
}
