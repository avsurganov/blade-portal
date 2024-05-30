package dev.surganov.bladesapi.crews.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._
import scala.collection.immutable

sealed trait Reputation extends EnumEntry

object Reputation extends BladesEnum[Reputation] with Enum[Reputation] with PlayJsonEnum[Reputation] {
  val values: immutable.IndexedSeq[Reputation] = findValues

  case object Ambitious extends Reputation
  case object Brutal extends Reputation
  case object Daring extends Reputation
  case object Honorable extends Reputation
  case object Professional extends Reputation
  case object Savvy extends Reputation
  case object Subtle extends Reputation
  case object Strange extends Reputation
}
