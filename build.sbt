import NativePackagerKeys._

name := """kennerspiel"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

maintainer := "Philihp Busby <philihp@gmail.com>"

dockerBaseImage := "dockerfile/java:oracle-java8"

dockerExposedPorts in Docker := Seq(9000)

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.philihp" % "weblabora" % "2.0.2"
)
