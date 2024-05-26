package dev.surganov.bladesapi.util.config

import pureconfig._
import pureconfig.generic.auto._

case class AppConfig(host: String, port: Int, url: String)

object Config {
  def load(): AppConfig = {
    EnvLoader.loadEnvFile()
    ConfigSource.default.at("app").loadOrThrow[AppConfig]
  }
}
