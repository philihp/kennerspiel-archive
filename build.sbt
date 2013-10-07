name := "Kennerspiel"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.guava" % "guava" % "15.0",
  "mysql" % "mysql-connector-java" % "5.1.24"
)

play.Project.playJavaSettings
