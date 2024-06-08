package dev.surganov.bladesapi.config

import dev.surganov.bladesapi.util.LoggerAccess
import pureconfig._
import pureconfig.error.ConfigReaderException
import pureconfig.generic.ProductHint
import pureconfig.generic.auto._

case class AppConfig(
    host: String,
    port: Int,
    url: String,
    version: String,
    apiToken: String,
    dallEApiKey: String,
    cloudinaryUrl: String
)

object Config extends LoggerAccess {
  implicit def hint[T]: ProductHint[T] = ProductHint[T](ConfigFieldMapping(CamelCase, CamelCase))

  def load(): AppConfig = {
    EnvLoader.loadEnvFile()
    try {
      ConfigSource.default.at("app").loadOrThrow[AppConfig]
    } catch {
      case e: ConfigReaderException[_] =>
        log.error(ConfigSource.default.at("app").value().toString)
        throw new RuntimeException(s"Failed to load configuration: ${e.failures.toList.mkString(", ")}")
    }
  }
}
