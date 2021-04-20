import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "cardealership",
    libraryDependencies += scalaTest % Test,

    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    libraryDependencies += "org.postgresql" % "postgresql" % "42.2.19", 

    // https://mvnrepository.com/artifact/io.argonaut/argonaut
    libraryDependencies += "io.argonaut" %% "argonaut" % "6.3.3", 

    // https://mvnrepository.com/artifact/org.scalaz/scalaz-core
    libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.4.0-M7", 

    // https://mvnrepository.com/artifact/io.argonaut/argonaut-scalaz
    libraryDependencies += "io.argonaut" %% "argonaut-scalaz" % "6.3.3"


  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
