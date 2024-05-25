package dev.surganov.bladesapi.swagger

import com.github.swagger.pekko.model.Info
import dev.surganov.bladesapi.playbooks.routes.PlaybookRoutes

/** Sample SwaggerDocService, replace values with those applicable your application.
  * By default, a swagger UI is made available too on the default routes. If you don't need the UI, or want
  * to load the UI in another way, replace [[SwaggerHttpWithUiService]] with [[com.github.swagger.pekko.SwaggerHttpService]]
  */
object SwaggerDocService extends SwaggerHttpWithUiService {
  override val apiClasses: Set[Class[_]] = Set(
    classOf[PlaybookRoutes],
  )
  override val host = "localhost:8080"
  override val info: Info = Info(version = "1.0")
//  override val externalDocs: Option[ExternalDocumentation] = Some(
//    new ExternalDocumentation().description("Core Docs").url("http://acme.com/docs")
//  )
  //use io.swagger.v3.oas.models.security.SecurityScheme to document authn requirements for API
  //override val securitySchemeDefinitions = Map("basicAuth" -> new SecurityScheme())
  override val unwantedDefinitions: Seq[String] = Seq("Function1", "Function1RequestContextFutureRouteResult")
}
