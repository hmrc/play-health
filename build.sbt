import play.sbt.PlayImport.PlayKeys.{playMonitoredFiles}
import uk.gov.hmrc.DefaultBuildSettings.targetJvm
import uk.gov.hmrc.playcrosscompilation.PlayVersion.Play25

 def loadObject[T](objectName: String): T = {
   import scala.reflect.runtime.universe
   val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
   val module = runtimeMirror.staticModule(objectName)
   val obj = runtimeMirror.reflectModule(module)
   obj.instance.asInstanceOf[T]
 }

lazy val library = Project("play-health", file("."))
  .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 3,
    makePublicallyAvailableOnBintray := true,
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
      if (PlayCrossCompilation.playVersion == Play25) loadObject[play.routes.compiler.RoutesGenerator]("play.routes.compiler.StaticRoutesGenerator") // not available on classpath with Play_27
      else InjectedRoutesGenerator
    }
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)
