package dev.surganov.bladesapi.cohorts.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._

import scala.collection.immutable

sealed trait Edge extends EnumEntry

object Edge extends BladesEnum[Edge] with Enum[Edge] with PlayJsonEnum[Edge] {
  val values: immutable.IndexedSeq[Edge] = findValues

  case object Fearsome extends Edge
  case object Independent extends Edge
  case object Loyal extends Edge
  case object Tenacious extends Edge
}
