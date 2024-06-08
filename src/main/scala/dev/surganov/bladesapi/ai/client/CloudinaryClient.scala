package dev.surganov.bladesapi.ai.client

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import dev.surganov.bladesapi.util.LoggerAccess
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import org.apache.pekko.stream.Materializer
import org.apache.pekko.util.ByteString

import scala.concurrent.{ExecutionContext, Future}

class CloudinaryClient(cloudinary: Cloudinary)(implicit ec: ExecutionContext, mat: Materializer, system: ActorSystem[_])
    extends LoggerAccess {
  private def downloadImage(characterId: String, url: String): Future[ByteString] = {
    log.info(s"Downloading Image Data from OpenAI for character: $characterId...")
    Http().singleRequest(HttpRequest(uri = url)).flatMap {
      case HttpResponse(StatusCodes.OK, _, entity, _) =>
        log.info(s"Successfully downloaded OpenAI Image data for character: $characterId")
        entity.dataBytes.runFold(ByteString.empty)(_ ++ _)
      case HttpResponse(code, _, _, _) =>
        log.error("Failed to download image data")
        Future.failed(new RuntimeException(s"Failed to fetch image, status code: $code"))
    }
  }

  private def uploadToCloudinary(data: ByteString, characterId: String): Future[String] = Future {
    log.info(s"Uploading Image to Cloudinary for character: $characterId...")
    val uploadResult = cloudinary.uploader().upload(data.toArray, ObjectUtils.asMap("public_id", characterId))
    log.info(s"Successfully uploaded image to Cloudinary $characterId...")
    uploadResult.get("url").toString
  }

  def processImage(url: String, characterId: String): Future[String] = {
    for {
      imageData <- downloadImage(characterId, url)
      uploadedUrl <- uploadToCloudinary(imageData, characterId)
    } yield uploadedUrl
  }
}
