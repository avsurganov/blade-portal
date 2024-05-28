package dev.surganov.bladesapi.playbooks.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._

import scala.collection.immutable

sealed trait TraumaCondition extends EnumEntry
object TraumaCondition extends BladesEnum[TraumaCondition] with Enum[TraumaCondition] with PlayJsonEnum[TraumaCondition] {
  val values: immutable.IndexedSeq[TraumaCondition] = findValues

  case object Cold extends TraumaCondition
  case object Haunted extends TraumaCondition
  case object Obsessed extends TraumaCondition
  case object Paranoid extends TraumaCondition
  case object Reckless extends TraumaCondition
  case object Soft extends TraumaCondition
  case object Unstable extends TraumaCondition
  case object Vicious extends TraumaCondition
}
