package dev.surganov.bladesapi.util
import org.apache.pekko.http.scaladsl.model.{ContentTypes, HttpEntity}
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

trait JsonContentTypeSupport {
  private def jsonRoute(route: Route): Route = {
    mapResponse { response =>
      response.withEntity(HttpEntity(ContentTypes.`application/json`, response.entity.dataBytes))
    } {
      route
    }
  }

  def json(getRoute: => Route): Route = {
    jsonRoute(getRoute)
  }
}
