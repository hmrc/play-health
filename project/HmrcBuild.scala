import _root_.play.core.PlayVersion
import play.routes.compiler.StaticRoutesGenerator
import sbt.Keys._
import sbt._
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc._
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {

  import uk.gov.hmrc.DefaultBuildSettings
  import DefaultBuildSettings._
  import play.core.PlayVersion
  import play.sbt.PlayImport._
  import play.sbt.routes.RoutesKeys.routesGenerator

  val nameApp = "play-health"

  val appDependencies = Seq(
    "com.typesafe.play" %% "play" % PlayVersion.current,
    ws % "provided",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % "test",
    "org.pegdown" % "pegdown" % "1.4.2" % "test"
  )

  lazy val playHealth = Project(nameApp, file("."))
    .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      targetJvm := "jvm-1.7",
      scalaVersion := "2.11.7",
      libraryDependencies ++= appDependencies,
      crossScalaVersions := Seq("2.11.7"),
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"),
        Resolver.typesafeRepo("releases")
      ),
      routesGenerator := StaticRoutesGenerator
    )

}
