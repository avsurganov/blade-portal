import Dependencies.*
import sbt.Keys.*
import sbtassembly.AssemblyPlugin.autoImport._

name := "Blades API"

version := "0.1"

scalaVersion := "2.13.12"

organization := "dev.surganov.bladesapi"

// Swagger
val swaggerDependencies = Seq(
  "jakarta.ws.rs" % "jakarta.ws.rs-api" % "4.0.0",
  "com.github.swagger-akka-http" %% "swagger-pekko-http" % "2.12.0",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.12.3",
  "com.github.swagger-akka-http" %% "swagger-enumeratum-module" % "2.9.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % Versions.jackson,
  "io.swagger.core.v3" % "swagger-jaxrs2-jakarta" % Versions.swagger,
)

libraryDependencies ++= Seq(
  "pl.iterators" %% "kebs-spray-json" % "1.9.7",
  // Pekko
  "org.apache.pekko" %% "pekko-http" % Versions.pekko,
  "org.apache.pekko" %% "pekko-http-spray-json" % Versions.pekko,
  "org.apache.pekko" %% "pekko-http-cors" % Versions.pekko,
  "org.apache.pekko" %% "pekko-actor-typed" % Versions.pekko,
  "org.apache.pekko" %% "pekko-stream" % Versions.pekko,
  // Logging
  "org.apache.pekko" %% "pekko-slf4j" % Versions.pekko,
  "ch.qos.logback" % "logback-classic" % "1.5.6",
  // JSON
  "com.typesafe.play" %% "play-json" % "2.10.5",
  // Enums
  "com.beachape" %% "enumeratum" % "1.7.3",
  "com.beachape" %% "enumeratum-play-json" % "1.8.0",
  // HTML Render
  "com.vladsch.flexmark" % "flexmark-all" % "0.64.8",
  // ENV Support
  "com.github.pureconfig" %% "pureconfig" % "0.17.6",
) ++ swaggerDependencies

ThisBuild / scalafmtOnCompile := true

// Assembly settings
assembly / mainClass := Some("dev.surganov.bladesapi.Api") // Replace with your main class

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case x                        => MergeStrategy.first
}
