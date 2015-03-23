name := "vaadin-in-akka"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions += "-target:jvm-1.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

initialize := {
 val _ = initialize.value
 val javaSpecVersion = sys.props("java.specification.version")
 if (javaSpecVersion != "1.8")
   sys.error(s"Java 8 is required for this project. (found $javaSpecVersion)")
}

crossPaths := false

autoScalaLibrary := false

resolvers ++= Seq(
  "Vaadin Snapshots" at "http://oss.sonatype.org/content/repositories/vaadin-snapshots/",
  "Vaadin add-ons" at "http://maven.vaadin.com/vaadin-addons"
)

val jettyVersion = "9.2.10.v20150310"
val vaadinVersion = "7.4.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "com.vaadin" % "vaadin-server" % vaadinVersion,
  "com.vaadin" % "vaadin-push" % vaadinVersion,
  "com.vaadin" % "vaadin-themes" % vaadinVersion,
  "com.vaadin.addon" % "vaadin-charts" % "1.1.8",
  "javax" % "javaee-web-api" % "7.0",
  "org.eclipse.jetty.aggregate" % "jetty-all" % jettyVersion,
  "com.vaadin" % "vaadin-client-compiler" % vaadinVersion % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-plus" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-annotations" % jettyVersion % "container"
)

vaadinWebSettings

// Using this you can avoid lengthy GWT compilation on each launch
//enableCompileVaadinWidgetsets in resourceGenerators := false
