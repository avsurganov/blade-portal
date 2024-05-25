package dev.surganov.bladesapi

import dev.surganov.bladesapi.common.{AdditionalRoutes, StatusResponse}
import dev.surganov.bladesapi.playbooks.routes.PlaybookRoutes
import dev.surganov.bladesapi.swagger.SwaggerDocService
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server._

trait AppRoutes extends AdditionalRoutes with PlaybookRoutes {
  private def baseRoutes: Seq[Route] = Seq(
    pathSingleSlash {
      get {
        val response = StatusResponse("ok")
        complete(response)
      }
    }
  )

  def aggregatedRoutes: Route = concatRoutes(baseRoutes ++ additionalRoutes ++ Seq(SwaggerDocService.routes))

  private def concatRoutes(routes: Seq[Route]): Route = {
    routes.reduceOption(_ ~ _).getOrElse(reject)
  }
}
