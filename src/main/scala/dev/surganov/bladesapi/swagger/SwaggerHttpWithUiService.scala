package dev.surganov.bladesapi.swagger

import com.github.swagger.pekko.SwaggerHttpService
import org.apache.pekko.http.scaladsl.model.StatusCodes.PermanentRedirect
import org.apache.pekko.http.scaladsl.server.Route

import scala.annotation.tailrec

/** SwaggerHttpService along with swagger-ui.
  *
  * Swagger-UI is now delegated to https://petstore.swagger.io but with a 'url' parameter that points
  * at the 'swagger.json' generated by this example.
  */
trait SwaggerHttpWithUiService extends SwaggerHttpService {

  private val swaggerUiRoute = {
    pathPrefix(apiDocsPath) {
      val pathInit = removeTrailingSlashIfNecessary(apiDocsPath)
      redirect(s"https://petstore.swagger.io/?url=http://localhost:8080/$pathInit/swagger.json", PermanentRedirect)
    }
  }

  override val routes: Route = super.routes ~ swaggerUiRoute

  @tailrec
  private def removeTrailingSlashIfNecessary(path: String): String =
    if (path.endsWith("/")) removeTrailingSlashIfNecessary(path.substring(0, path.length - 1)) else path
}