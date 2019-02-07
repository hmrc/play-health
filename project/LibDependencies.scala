import PlayCrossCompilation._
import play.core.PlayVersion
import sbt._

object LibDependencies {

  val compile: Seq[ModuleID] = Seq(
    "com.typesafe.play" %% "play" % PlayVersion.current,
    // force dependencies due to security flaws found in jackson-databind < 2.9.x using XRay
    "com.fasterxml.jackson.core"     % "jackson-core"            % "2.9.7",
    "com.fasterxml.jackson.core"     % "jackson-databind"        % "2.9.7",
    "com.fasterxml.jackson.core"     % "jackson-annotations"     % "2.9.7",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8"   % "2.9.7",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.9.7",
    // force dependencies due to security flaws found in xercesImpl 2.11.0
    "xerces" % "xercesImpl" % "2.12.0"
  )

  val test: Seq[ModuleID] = dependencies(
    shared = Seq(
      "org.pegdown"   % "pegdown"    % "1.6.0" % Test,
      "org.scalatest" %% "scalatest" % "3.0.5" % Test
    ),
    play25 = Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.1" % Test
    ),
    play26 = Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
    )
  )

}
