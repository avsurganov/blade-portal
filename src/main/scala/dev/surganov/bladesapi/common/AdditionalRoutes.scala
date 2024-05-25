package dev.surganov.bladesapi.common

import org.apache.pekko.http.scaladsl.server.Route

trait AdditionalRoutes {
  def additionalRoutes: Seq[Route] = Seq.empty[Route]
}