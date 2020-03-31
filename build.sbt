import PlayCrossCompilation._
import play.sbt.PlayImport.PlayKeys._
import uk.gov.hmrc.DefaultBuildSettings.targetJvm
import uk.gov.hmrc.playcrosscompilation.PlayVersion.Play25

val libName = "play-health"

 def loadObject[T](objectName: String): T = {
   import scala.reflect.runtime.universe
   val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
   val module = runtimeMirror.staticModule(objectName)
   val obj = runtimeMirror.reflectModule(module)
   obj.instance.asInstanceOf[T]
 }

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
    crossScalaVersions := List("2.11.12", "2.12.8"),
    targetJvm := "jvm-1.8",
    libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value,
    routesGenerator    := {
      if (playVersion == Play25) loadObject[play.routes.compiler.RoutesGenerator]("play.routes.compiler.StaticRoutesGenerator") // not available on classpath with Play_27
      else InjectedRoutesGenerator
    },
    playCrossCompilationSettings
  )
