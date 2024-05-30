package dev.surganov.bladesapi.cohorts.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._

import scala.collection.immutable

sealed trait Flaw extends EnumEntry

object Flaw extends BladesEnum[Flaw] with Enum[Flaw] with PlayJsonEnum[Flaw] {
  val values: immutable.IndexedSeq[Flaw] = findValues

  case object Principled extends Flaw
  case object Savage extends Flaw
  case object Unreliable extends Flaw
  case object Wild extends Flaw
}
