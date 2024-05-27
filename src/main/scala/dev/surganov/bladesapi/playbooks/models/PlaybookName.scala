package dev.surganov.bladesapi.playbooks.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._

import scala.collection.immutable

sealed trait PlaybookName extends EnumEntry

object PlaybookName extends BladesEnum[PlaybookName] with Enum[PlaybookName] with PlayJsonEnum[PlaybookName] {
  val values: immutable.IndexedSeq[PlaybookName] = findValues

  case object Cutter extends PlaybookName
  case object Hound extends PlaybookName
  case object Leech extends PlaybookName
  case object Lurk extends PlaybookName
  case object Slide extends PlaybookName
  case object Spider extends PlaybookName
  case object Whisper extends PlaybookName
}
