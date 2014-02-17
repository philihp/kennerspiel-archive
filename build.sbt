name := "Kennerspiel"

version := "1.0-SNAPSHOT"

resolvers += "philihp" at "https://philihp.com/repo/"

publishTo := Some(Resolver.file("file", new File("/srv/www/philihp.com/public_html/repo")))

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.guava" % "guava" % "15.0",
  "mysql" % "mysql-connector-java" % "5.1.24",
  "kennerspiel-interface" % "kennerspiel-interface_2.10" % "1.0-SNAPSHOT",
  "kennerspiel-agricola2p" % "kennerspiel-agricola2p_2.10" % "1.0-SNAPSHOT"
)

play.Project.playJavaSettings
