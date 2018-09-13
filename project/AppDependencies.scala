import play.core.PlayVersion
import sbt._
import uk.gov.hmrc.Dependencies

object AppDependencies {

  val compile = Seq(
    "com.typesafe.play" %% "play" % PlayVersion.current
  )

  val test = Dependencies(
    common = Seq(
      "org.pegdown"            % "pegdown"             % "1.6.0" % Test,
      "org.scalatest"          %% "scalatest"          % "3.0.5" % Test
    ),
    play25 = Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.1" % Test
    ),
    play26 = Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
    )
  )
}
