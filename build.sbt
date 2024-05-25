name := "Blades API"

version := "0.1"

scalaVersion := "2.13.12"

organization := "dev.surganov.bladesapi"

val pekkoVersion = "1.0.1"
val pekkoHttpVersion = "1.0.1"
val jacksonVersion = "2.16.1"
val swaggerVersion = "2.2.20"

// Swagger
val swaggerDependencies = Seq(
  "jakarta.ws.rs" % "jakarta.ws.rs-api" % "3.0.0",
  "com.github.swagger-akka-http" %% "swagger-pekko-http" % "2.12.0",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.12.3",
  "com.github.swagger-akka-http" %% "swagger-enumeratum-module" % "2.9.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "io.swagger.core.v3" % "swagger-jaxrs2-jakarta" % swaggerVersion
)

libraryDependencies ++= Seq(
  "pl.iterators" %% "kebs-spray-json" % "1.9.7",
  // Pekko
  "org.apache.pekko" %% "pekko-http" % pekkoVersion,
  "org.apache.pekko" %% "pekko-http-spray-json" % pekkoVersion,
  "org.apache.pekko" %% "pekko-http-cors" % pekkoHttpVersion,
  "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
  "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
  // Logging
  "org.apache.pekko" %% "pekko-slf4j" % pekkoVersion,
  "org.slf4j" % "slf4j-simple" % "2.0.12",
  "com.typesafe.play" %% "play-json" % "2.10.5",
  // Enums
  "com.beachape" %% "enumeratum" % "1.7.3",
  "com.beachape" %% "enumeratum-play-json" % "1.8.0",
) ++ swaggerDependencies

ThisBuild / scalafmtOnCompile := true
