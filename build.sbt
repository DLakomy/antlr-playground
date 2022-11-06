val scala3Version = "3.2.0"

enablePlugins(Antlr4Plugin)

val antlrVersion = "4.11.1"

Antlr4 / antlr4Version := antlrVersion
Antlr4 / antlr4PackageName := Some("antlrPlayground")
Antlr4 / antlr4GenListener := true
Antlr4 / antlr4GenVisitor := true

lazy val root = project
  .in(file("."))
  .settings(
    name := "antlrPlayground",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test,
    libraryDependencies += "org.antlr" % "antlr4-runtime" % antlrVersion
  )
