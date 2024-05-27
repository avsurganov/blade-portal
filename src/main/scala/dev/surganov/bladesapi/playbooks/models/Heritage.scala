package dev.surganov.bladesapi.playbooks.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._

import scala.collection.immutable

sealed trait Heritage extends EnumEntry

object Heritage extends BladesEnum[Heritage] with Enum[Heritage] with PlayJsonEnum[Heritage] {
  val values: immutable.IndexedSeq[Heritage] = findValues

  case object Akoros extends Heritage
  case object TheDaggerIsles extends Heritage {
    override def toString: String = "The Dagger Isles"
  }
  case object Iruvia extends Heritage
  case object Severos extends Heritage
  case object Skovlan extends Heritage
  case object Tycheros extends Heritage
}
