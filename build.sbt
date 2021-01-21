import play.sbt.PlayImport.PlayKeys.playMonitoredFiles
import uk.gov.hmrc.DefaultBuildSettings.targetJvm

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
    majorVersion := 4,
    makePublicallyAvailableOnBintray := true,
    scalaVersion := "2.12.12",
    targetJvm := "jvm-1.8",
    libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    routesGenerator := InjectedRoutesGenerator,
    playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)
