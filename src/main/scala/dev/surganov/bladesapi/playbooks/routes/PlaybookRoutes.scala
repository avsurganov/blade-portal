package dev.surganov.bladesapi.playbooks.routes

import dev.surganov.bladesapi.common.{AdditionalRoutes, StatusResponse}
import dev.surganov.bladesapi.data.Playbooks
import dev.surganov.bladesapi.playbooks.models.Playbook
import dev.surganov.bladesapi.util.{JsonSupport, LoggerAccess}
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.{Operation, Parameter}
import jakarta.ws.rs.{GET, Path}
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server._

@Path("/playbooks")
trait PlaybookRoutes extends AdditionalRoutes with JsonSupport with LoggerAccess {
  override def additionalRoutes: Seq[Route] = super.additionalRoutes ++ Seq(
    getAllPlaybooks ~ getPlaybookByName
  )

  @GET
  @Operation(
    summary = "Get all playbooks",
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        description = "All playbooks retrieved",
        content = Array(new Content(schema = new Schema(implementation = classOf[List[Playbook]])))
      ),
      new ApiResponse(responseCode = "500", description = "Internal server error")
    )
  )
  private def getAllPlaybooks: Route = pathEnd {
    get {
      log.info("Retrieving all playbooks")
      complete(Playbooks.all)
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
        content = Array(new Content(schema = new Schema(implementation = classOf[StatusResponse])))
      ),
      new ApiResponse(responseCode = "404", description = "Playbook not found")
    )
  )
  private def getPlaybookByName: Route = path(Segment) { name =>
    get {
      log.info(s"Retrieving playbook: $name")
      rejectEmptyResponse {
        complete(StatusResponse("A playbook"))
      }
    }
  }
}
