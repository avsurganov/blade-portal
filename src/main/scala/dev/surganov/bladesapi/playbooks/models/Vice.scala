package dev.surganov.bladesapi.playbooks.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._

import scala.collection.immutable

sealed trait Vice extends EnumEntry

object Vice extends BladesEnum[Vice] with Enum[Vice] with PlayJsonEnum[Vice] {
  val values: immutable.IndexedSeq[Vice] = findValues

  case object Faith extends Vice
  case object Gambling extends Vice
  case object Luxury extends Vice
  case object Obligation extends Vice
  case object Pleasure extends Vice
  case object Stupor extends Vice
  case object Weird extends Vice
}
