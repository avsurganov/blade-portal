package dev.surganov.bladesapi.util

import org.apache.pekko.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import play.api.libs.json.{Format, Json}
import spray.json.{DefaultJsonProtocol, RootJsonFormat, _}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit def playJsonFormat[T](implicit format: Format[T]): RootJsonFormat[T] = new RootJsonFormat[T] {
    override def write(obj: T): JsValue = Json.toJson(obj).toString().parseJson
    override def read(json: JsValue): T = Json.fromJson[T](Json.parse(json.toString())).get
  }
}
