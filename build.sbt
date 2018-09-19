import PlayCrossCompilation._
import play.sbt.PlayImport.PlayKeys._
import uk.gov.hmrc.DefaultBuildSettings.targetJvm
import uk.gov.hmrc.playcrosscompilation.PlayVersion.Play25

val libName = "play-health"

lazy val library = Project(libName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    makePublicallyAvailableOnBintray := true,
    majorVersion                     := 3
  )
  .settings(
    name := libName,
    scalaVersion := "2.11.12",
    targetJvm := "jvm-1.8",
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value,
    routesGenerator    := {
      if (playVersion == Play25) StaticRoutesGenerator
      else InjectedRoutesGenerator
    },
    playCrossCompilationSettings
  )
