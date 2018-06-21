import sbt._

object AppDependencies {

  import play.core.PlayVersion
  import play.sbt.PlayImport._

  val compile = Seq (
    "com.typesafe.play"       %% "play"               % PlayVersion.current,
    ws % "provided"
  )

  val test = Seq (
    "org.scalatest"           %% "scalatest"          % "3.0.3" % "test",
    "org.scalatestplus.play"  %% "scalatestplus-play" % "3.1.2" % "test",
    "org.pegdown"             %  "pegdown"            % "1.6.0" % "test"
  )
}
