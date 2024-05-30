package dev.surganov.bladesapi.crews.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._
import scala.collection.immutable

sealed trait HoldStatus extends EnumEntry

object HoldStatus extends BladesEnum[HoldStatus] with Enum[HoldStatus] with PlayJsonEnum[HoldStatus] {
  val values: immutable.IndexedSeq[HoldStatus] = findValues

  case object Weak extends HoldStatus
  case object Strong extends HoldStatus
}
