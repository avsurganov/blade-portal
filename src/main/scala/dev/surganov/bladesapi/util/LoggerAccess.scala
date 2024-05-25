package dev.surganov.bladesapi.util

import org.slf4j.{Logger, LoggerFactory}

trait LoggerAccess {
  val log: Logger = LoggerFactory.getLogger(this.getClass)
}
