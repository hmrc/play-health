import uk.gov.hmrc.DefaultBuildSettings.targetJvm

val libName = "play-health"

lazy val library = Project(libName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)
  .settings(
    name := libName,
    scalaVersion := "2.11.12",
    targetJvm := "jvm-1.8",
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    )
  )
