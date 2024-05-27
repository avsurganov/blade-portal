package dev.surganov.bladesapi.config

import dev.surganov.bladesapi.util.LoggerAccess

import scala.io.Source

object EnvLoader extends LoggerAccess {
  def loadEnvFile(): Unit = {
    val source = Source.fromFile(".env")
    for (line <- source.getLines()) {
      val Array(key, value) = line.split("=", 2)
      sys.props += (key -> value)
    }
    source.close()
  }
}
