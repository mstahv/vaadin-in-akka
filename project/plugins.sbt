// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "sbt-vaadin-plugin repo" at "http://henrikerola.github.io/repository/snapshots"

addSbtPlugin("org.vaadin.sbt" % "sbt-vaadin-plugin" % "1.1-SNAPSHOT")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.3.0")
