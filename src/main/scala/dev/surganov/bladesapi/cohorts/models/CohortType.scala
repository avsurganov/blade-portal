package dev.surganov.bladesapi.cohorts.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._

import scala.collection.immutable

sealed trait CohortType extends EnumEntry

object CohortType extends BladesEnum[CohortType] with Enum[CohortType] with PlayJsonEnum[CohortType] {
  val values: immutable.IndexedSeq[CohortType] = findValues

  case object Adepts extends CohortType
  case object Rooks extends CohortType
  case object Rovers extends CohortType
  case object Skulks extends CohortType
  case object Thugs extends CohortType

  case object Expert extends CohortType
}
