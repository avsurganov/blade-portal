package dev.surganov.bladesapi.crews.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._
import scala.collection.immutable

sealed trait CrewName extends EnumEntry
object CrewName extends BladesEnum[CrewName] with Enum[CrewName] with PlayJsonEnum[CrewName] {
  val values: immutable.IndexedSeq[CrewName] = findValues

  case object Assassins extends CrewName
  case object Bravos extends CrewName
  case object Cult extends CrewName
  case object Hawkers extends CrewName
  case object Smugglers extends CrewName
  case object Shadows extends CrewName
}
