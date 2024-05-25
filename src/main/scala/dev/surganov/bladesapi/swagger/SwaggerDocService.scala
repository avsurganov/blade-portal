package dev.surganov.bladesapi.swagger

import com.github.swagger.pekko.model.{Info, License, Contact}
import dev.surganov.bladesapi.playbooks.PlaybookService
import io.swagger.v3.oas.models.ExternalDocumentation

/** Sample SwaggerDocService, replace values with those applicable your application.
  * By default, a swagger UI is made available too on the default routes. If you don't need the UI, or want
  * to load the UI in another way, replace [[SwaggerHttpWithUiService]] with [[com.github.swagger.pekko.SwaggerHttpService]]
  */
object SwaggerDocService extends SwaggerHttpWithUiService {
  override val apiClasses: Set[Class[_]] = Set(
    PlaybookService.getClass
  )
  override val host = "localhost:8080"
  override val info: Info = Info(
    description = "An API for Blades in the Dark",
    contact = Some(
      Contact(
        name = "Vlad Surganov",
        url = "https://github.com/avsurganov/blade-portal-api/issues",
        email = "avsurganov@gmail.com"
      )
    ),
    license = Some(
      License(
        name = "Creative Commons Attribution 4.0 Unported License",
        url = "https://creativecommons.org/licenses/by/4.0/"
      )
    ),
    version = "0.1"
  )
  override val externalDocs: Option[ExternalDocumentation] = Some(
    new ExternalDocumentation().description("GitHub").url("https://github.com/avsurganov/blade-portal-api")
  )
  //use io.swagger.v3.oas.models.security.SecurityScheme to document authn requirements for API
  //override val securitySchemeDefinitions = Map("basicAuth" -> new SecurityScheme())
  override val unwantedDefinitions: Seq[String] = Seq("Function1", "Function1RequestContextFutureRouteResult")
}
