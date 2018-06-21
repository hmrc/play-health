import uk.gov.hmrc.DefaultBuildSettings.targetJvm

enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)

name := "play-health"

scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.11.8")
targetJvm := "jvm-1.8"

libraryDependencies ++= AppDependencies()

resolvers := Seq(
  Resolver.bintrayRepo("hmrc", "releases"),
  "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/"
)
