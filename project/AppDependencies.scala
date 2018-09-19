import PlayCrossCompilation._
import play.core.PlayVersion
import sbt._
import uk.gov.hmrc.playcrosscompilation.PlayVersion._

object AppDependencies {

  val compile = Seq(
    "com.typesafe.play" %% "play" % PlayVersion.current
  )

  val test = DependenciesSeq(
    "org.pegdown"            % "pegdown"             % "1.6.0" % Test,
    "org.scalatest"          %% "scalatest"          % "3.0.5" % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.1" % Test crossPlay Play25,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test crossPlay Play26
  )
}
