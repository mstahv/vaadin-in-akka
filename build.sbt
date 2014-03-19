
scalacOptions += "-target:jvm-1.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

resolvers += "Vaadin Snapshots" at "http://oss.sonatype.org/content/repositories/vaadin-snapshots/"

resolvers += "Vaadin add-ons" at "http://maven.vaadin.com/vaadin-addons"


libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.0"

libraryDependencies += "com.vaadin" % "vaadin-server" % "7.1.12"

libraryDependencies += "com.vaadin" % "vaadin-push" % "7.1.12"

libraryDependencies += "com.vaadin" % "vaadin-themes" % "7.1.12"

libraryDependencies += "com.vaadin.addon" % "vaadin-charts" % "1.1.5"

libraryDependencies += "com.vaadin" % "vaadin-client-compiler" % "7.1.12" % "provided"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"



