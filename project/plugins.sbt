// Uncomment to get less information during initialization
//logLevel := Level.Warn

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % Option(System.getProperty("play.version")).getOrElse("2.3.7"))

// web plugins

//fuck your coffeescript
//addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.0")

//uncomment if you don't want it to yell at you for debugger statements
//addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.7")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.0.0")

//addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-uglify" % "1.0.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")

// React
addSbtPlugin("com.github.ddispaltro" % "sbt-reactjs" % "0.5.0")