import uk.gov.hmrc.DefaultBuildSettings.targetJvm

enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)

name := "play-health"

scalaVersion := "2.11.12"
crossScalaVersions := Seq("2.11.12", "2.12.6")
targetJvm := "jvm-1.8"

libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test

resolvers := Seq(
  Resolver.bintrayRepo("hmrc", "releases"),
  "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/"
)
