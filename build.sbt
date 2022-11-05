val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "antlrPlayground",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test,
    libraryDependencies += "org.antlr" % "antlr4-runtime" % "4.11.1"
  )
