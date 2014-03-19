import sbt._
import Keys._
import org.vaadin.sbt.VaadinPlugin._

// TODO Copy pasted from Risto's project, don't work yet
object ApplicationBuild extends Build {

  val appName           = "reactive-stocks"
  val appVersion        = "1.0-SNAPSHOT"
  val appScalaVersion   = "2.10.3"
  val appVaadinVersion  = "7.2-SNAPSHOT"

  val repositories = Seq("Vaadin Snapshots" at "http://oss.sonatype.org/content/repositories/vaadin-snapshots/",
                         "Vaadin add-ons" at "http://maven.vaadin.com/vaadin-addons",
                         "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/")

  val appSettings =
  vaadinWebSettings ++
  Seq(libraryDependencies := appDependencies,
      					version := appVersion,
      					scalaVersion := appScalaVersion,
      					resolvers := repositories,
                javaOptions in compileVaadinWidgetsets := Seq("-Xss8M", "-Xmx512M", "-XX:MaxPermSize=512M"),
                vaadinWidgetsets := Seq("ui.Widgetset"),
                vaadinOptions in compileVaadinWidgetsets := Seq("-strict", "-draftCompile", "-localWorkers", "2" )

                //target in compileWidgetsets := (sourceDirectory in Compile).value / "webapp" / "VAADIN" / "widgetsets"
)
  lazy val app = Project(id = "reactive-stocks",
                            base = file("."),
                            settings = Project.defaultSettings ++ appSettings)
                  }
