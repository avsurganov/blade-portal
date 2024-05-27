package dev.surganov.bladesapi.playbooks.models
import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._

import scala.collection.immutable

sealed trait Background extends EnumEntry

object Background extends BladesEnum[Background] with Enum[Background] with PlayJsonEnum[Background] {
  val values: immutable.IndexedSeq[Background] = findValues

  case object Academic extends Background
  case object Labor extends Background
  case object Law extends Background
  case object Trade extends Background
  case object Military extends Background
  case object Noble extends Background
  case object Underworld extends Background
}
