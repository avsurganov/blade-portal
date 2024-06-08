package dev.surganov.bladesapi.ai.models

import dev.surganov.bladesapi.common.{SuccessResponse, SuccessResponseFormat}
import play.api.libs.json.{Format, Json}

case class ImageRequest(characterId: String, prompt: String)
object ImageRequest {
  implicit val format: Format[ImageRequest] = Json.format
}

case class DallEImageRequest(prompt: String, model: String = "dall-e-3", n: Int = 1, size: String = "1024x1024")
object DallEImageRequest {
  implicit val format: Format[DallEImageRequest] = Json.format
}

case class DallEImageResponse(data: List[ImageData])
object DallEImageResponse {
  implicit val format: Format[DallEImageResponse] = Json.format
}

case class BladesImageResponse(override val details: ImageData) extends SuccessResponse[ImageData](details = details)
object BladesImageResponse {
  implicit val format: Format[BladesImageResponse] = SuccessResponseFormat.format(BladesImageResponse.apply)
}

case class ImageData(url: String)
object ImageData {
  implicit val format: Format[ImageData] = Json.format
}
