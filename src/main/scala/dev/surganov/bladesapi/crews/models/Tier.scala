package dev.surganov.bladesapi.crews.models

import dev.surganov.bladesapi.common.BladesEnum
import enumeratum._
import scala.collection.immutable

sealed trait Tier extends EnumEntry

object Tier extends BladesEnum[Tier] with Enum[Tier] with PlayJsonEnum[Tier] {
  val values: immutable.IndexedSeq[Tier] = findValues

  case object Tier0 extends Tier {
    override def toString: String = "Tier 0"
  }
  case object TierI extends Tier {
    override def toString: String = "Tier I"
  }
  case object TierII extends Tier {
    override def toString: String = "Tier II"
  }
  case object TierIII extends Tier {
    override def toString: String = "Tier III"
  }
  case object TierIV extends Tier {
    override def toString: String = "Tier IV"
  }
  case object TierV extends Tier {
    override def toString: String = "Tier V"
  }
  case object TierVI extends Tier {
    override def toString: String = "Tier VI"
  }
}
