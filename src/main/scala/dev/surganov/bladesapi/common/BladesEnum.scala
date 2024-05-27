package dev.surganov.bladesapi.common

import enumeratum.{EnumEntry, Enum}

trait BladesEnum[A <: EnumEntry] { _: Enum[A] =>
  val values: IndexedSeq[A]
  def all: List[A] = values.toList
}
