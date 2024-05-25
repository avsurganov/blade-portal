package dev.surganov.bladesapi.util
import org.apache.pekko.http.scaladsl.model.ContentTypes
import org.apache.pekko.http.scaladsl.model.headers.`Content-Type`
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

trait JsonContentTypeSupport {
  private def jsonRoute(route: Route): Route = {
    respondWithHeader(`Content-Type`(ContentTypes.`application/json`)) {
      route
    }
  }

  def json(getRoute: => Route): Route = {
    jsonRoute(getRoute)
  }
}
