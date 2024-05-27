package dev.surganov.bladesapi.config

trait ConfigProvider {
  val config: AppConfig = Config.load()
}
