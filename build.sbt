import com.typesafe.sbt.SbtNativePackager.NativePackagerKeys._
import com.typesafe.sbt.SbtNativePackager._
import WebKeys._

name := "kennerspiel"

version := "1.0-SNAPSHOT"

resolvers += Resolver.mavenLocal

lazy val root = (project in file(".")).enablePlugins(PlayJava).enablePlugins(SbtWeb)

scalaVersion := "2.11.1"

// Dependencies------------
resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "xalan" % "serializer" % "2.7.2",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.philihp" % "weblabora" % "2.1.0-SNAPSHOT",
  "com.feth" %% "play-authenticate" % "0.6.8",
  "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars.bower" % "jquery" % "2.1.3",
  "org.webjars.bower" % "bootstrap" % "3.3.4" exclude("org.webjars", "jquery"),
  "org.webjars.bower" % "underscore" % "1.8.3",
  "org.webjars.bower" % "react" % "0.13.3"
)

pipelineStages := Seq(rjs, digest)

RjsKeys.paths += ("jsRoutes" -> ("/jsroutes" -> "empty:"))

emojiLogs