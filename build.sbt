name := "vaadin-in-akka"

version := "1.0-SNAPSHOT"

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

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.0",
  "com.vaadin" % "vaadin-server" % "7.3.5",
  "com.vaadin" % "vaadin-push" % "7.3.5",
  "com.vaadin" % "vaadin-themes" % "7.3.5",
  "com.vaadin.addon" % "vaadin-charts" % "1.1.5",
  "javax" % "javaee-web-api" % "7.0",
  "org.eclipse.jetty.aggregate" % "jetty-all" % "9.1.0.v20131115",
  "com.vaadin" % "vaadin-client-compiler" % "7.3.5" % "provided",
//  "javax" % "javaee-web-api" % "7.0" % "container",
  "org.eclipse.jetty" % "jetty-webapp" % "9.1.0.v20131115" % "container",
  "org.eclipse.jetty" % "jetty-plus" % "9.1.0.v20131115" % "container",
  "org.eclipse.jetty" % "jetty-annotations" % "9.1.0.v20131115" % "container"
)


// Needed for jetty-annotations to work with Java 8
dependencyOverrides ++= Set(
  "org.ow2.asm" % "asm-commons" % "5.0.3",
  "org.ow2.asm" % "asm" % "5.0.3"
)

vaadinWebSettings

// Using this you can avoid lengthy GWT compilation on each launch
// enableCompileVaadinWidgetsets in resourceGenerators := false



