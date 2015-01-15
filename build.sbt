name := "kennerspiel"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.philihp" % "weblabora" % "2.0.2"
)

play.Project.playJavaSettings
