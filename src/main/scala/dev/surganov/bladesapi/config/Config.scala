package dev.surganov.bladesapi.config

import pureconfig._
import pureconfig.generic.auto._

case class AppConfig(host: String, port: Int, url: String, version: String)

object Config {
  def load(): AppConfig = {
    EnvLoader.loadEnvFile()
    ConfigSource.default.at("app").loadOrThrow[AppConfig]
  }
}
