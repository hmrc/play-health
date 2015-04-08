import sbt._
import Keys._

object HmrcBuild extends Build {

  import uk.gov.hmrc.DefaultBuildSettings
  import DefaultBuildSettings._
  import uk.gov.hmrc.{SbtBuildInfo, ShellPrompt}
  import play.core.PlayVersion
  import play.PlayImport._

  val nameApp = "play-health"
  val versionApp = "0.8.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.typesafe.play" %% "play" % PlayVersion.current,
    ws % "provided",
    "org.scalatest" %% "scalatest" % "2.2.1" % "test",
    "org.pegdown" % "pegdown" % "1.4.2" % "test"
  )

  lazy val playHealth = Project(nameApp, file("."))
    .enablePlugins(play.PlayScala)
    .settings(version := versionApp)
    .settings(scalaSettings : _*)
    .settings(defaultSettings() : _*)
    .settings(
      targetJvm := "jvm-1.7",
      shellPrompt := ShellPrompt(versionApp),
      libraryDependencies ++= appDependencies,
      resolvers := Seq(
        Opts.resolver.sonatypeReleases
      ),
      crossScalaVersions := Seq("2.11.5")
    )
    .settings(SbtBuildInfo(): _*)

}