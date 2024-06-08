package dev.surganov.bladesapi.ai.client

import dev.surganov.bladesapi.ai.models.{DallEImageRequest, DallEImageResponse, ImageData}
import dev.surganov.bladesapi.config.ConfigProvider
import dev.surganov.bladesapi.util.LoggerAccess
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.model._
import org.apache.pekko.http.scaladsl.model.headers.{Accept, Authorization, OAuth2BearerToken}
import org.apache.pekko.http.scaladsl.unmarshalling.Unmarshal
import org.apache.pekko.stream.Materializer
import play.api.libs.json.{JsError, JsSuccess, Json}

import scala.concurrent.{ExecutionContext, Future}

class DallEClient(implicit ec: ExecutionContext, mat: Materializer, sys: ActorSystem[Any]) extends ConfigProvider with LoggerAccess {
  private def optimizePrompt(description: String): String = {
    s"Create a detailed portrait of a character from the game Blades in the Dark. Description: $description. Background: gloomy city of Duskvol. No text or additional elements."
  }

  def generateCharacterImage(
      characterId: String,
      prompt: String
  ): Future[ImageData] = {
    val requestPayload =
      Json.toJson(DallEImageRequest(optimizePrompt(prompt))).toString()
    val httpRequest = HttpRequest(
      method = HttpMethods.POST,
      uri = "https://api.openai.com/v1/images/generations",
      headers = List(Authorization(OAuth2BearerToken(config.dallEApiKey)), Accept(MediaTypes.`application/json`)),
      entity = HttpEntity(ContentTypes.`application/json`, requestPayload)
    )

    log.info(s"Generating DALL-E image for character: $characterId...")

    for {
      response <- Http().singleRequest(httpRequest)
      responseBody <- Unmarshal(response.entity).to[String]
    } yield {
      response.status match {
        case StatusCodes.OK =>
          Json.parse(responseBody).validate[DallEImageResponse] match {
            case JsSuccess(imageResponse, _) =>
              log.info(s"Successfully generated Image for character $characterId")
              imageResponse.data.head // Head should be fine since we're only getting a single image from DallE
            case JsError(errors) =>
              log.error("Encountered error when generating DallE image")
              throw new RuntimeException(s"Failed to parse response: $errors")
          }
        case _ =>
          throw new RuntimeException(s"Request failed with status ${response.status} and body $responseBody")
      }
    }
  }
}
