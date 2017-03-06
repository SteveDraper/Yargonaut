name := "Yargonaut"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.6"
libraryDependencies += "org.scalaz" %% "scalaz-effect" % "7.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5" % "test"
libraryDependencies += "io.argonaut" % "argonaut_2.11" % "6.1a"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
