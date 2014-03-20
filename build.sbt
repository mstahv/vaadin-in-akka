name := "vaadin-in-akka"

version := "1.0-SNAPSHOT"

scalacOptions += "-target:jvm-1.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

crossPaths := false

autoScalaLibrary := false

resolvers ++= Seq(
  "Vaadin Snapshots" at "http://oss.sonatype.org/content/repositories/vaadin-snapshots/",
  "Vaadin add-ons" at "http://maven.vaadin.com/vaadin-addons"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.0",
  "com.vaadin" % "vaadin-server" % "7.1.12",
  "com.vaadin" % "vaadin-push" % "7.1.12",
  "com.vaadin" % "vaadin-themes" % "7.1.12",
  "com.vaadin.addon" % "vaadin-charts" % "1.1.5",
  "com.vaadin" % "vaadin-client-compiler" % "7.1.12" % "provided",
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "9.1.0.v20131115" % "container",
  "org.eclipse.jetty" % "jetty-plus" % "9.1.0.v20131115" % "container",
  "org.eclipse.jetty" % "jetty-annotations" % "9.1.0.v20131115" % "container"
)


// Needed for jetty-annotations to work with Java 8
dependencyOverrides ++= Set(
  "org.ow2.asm" % "asm-commons" % "5.0",
  "org.ow2.asm" % "asm" % "5.0"
)

vaadinWebSettings

enableCompileVaadinWidgetsets in resourceGenerators := false



