package dev.surganov.bladesapi.config

import dev.surganov.bladesapi.util.LoggerAccess
import scala.io.Source
import scala.util.{Try, Failure, Success}

object EnvLoader extends LoggerAccess {
  def loadEnvFile(): Unit = {
    Try(Source.fromFile(".env")) match {
      case Success(source) =>
        for (line <- source.getLines()) {
          val Array(key, value) = line.split("=", 2)
          sys.props += (key -> value)
        }
        source.close()
      case Failure(_) =>
        log.warn("No .env file found, skipping environment variable loading from file.")
    }
  }
}
