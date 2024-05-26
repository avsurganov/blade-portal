package dev.surganov.bladesapi.util.config

trait ConfigProvider {
  val config: AppConfig = Config.load()
}
