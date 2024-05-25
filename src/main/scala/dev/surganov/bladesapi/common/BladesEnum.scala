package dev.surganov.bladesapi.common

trait BladesEnum[T] {
  val values: IndexedSeq[T]
  def all: List[T] = values.toList
}