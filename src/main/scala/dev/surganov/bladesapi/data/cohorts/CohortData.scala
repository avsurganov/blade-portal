package dev.surganov.bladesapi.data.cohorts

import dev.surganov.bladesapi.cohorts.models.{Cohort, CohortList, CohortType, Edge, Flaw}

object CohortData {
  def all: CohortList = CohortList(CohortType.all.map { _type => cohort(_type) })

  def cohort(_type: CohortType): Cohort = {
    Cohort(cohortType = _type, edges = Edge.all, flaws = Flaw.all)
  }
}
