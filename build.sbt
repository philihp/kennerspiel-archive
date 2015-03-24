import com.typesafe.sbt.SbtNativePackager.NativePackagerKeys._
import com.typesafe.sbt.SbtNativePackager._

name := "kennerspiel"

version := "1.0-SNAPSHOT"

resolvers += Resolver.mavenLocal

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

// Dependencies------------
resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "com.philihp" % "weblabora" % "2.0.3-SNAPSHOT",
  "com.feth" %% "play-authenticate" % "0.6.8",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)
