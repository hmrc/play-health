import sbt._
import Keys._
import play.Project._

object HmrcBuild extends Build {

  import uk.gov.hmrc.DefaultBuildSettings

  val nameApp = "play-health"
  val versionApp = "0.2.0"

  val appDependencies = Seq(
    "org.scalatest" %% "scalatest" % "2.2.0",
    "com.typesafe.play" %% "play" % "2.2.3",
    "org.pegdown" % "pegdown" % "1.4.2"
  )

  lazy val root = Project(nameApp, file("."), settings = DefaultBuildSettings(nameApp, versionApp, targetJvm = "jvm-1.7")()
    ++ playScalaSettings
    ++ Seq(
      libraryDependencies ++= appDependencies,
      resolvers := Seq(
        Opts.resolver.sonatypeReleases,
        Opts.resolver.sonatypeSnapshots,
        "typesafe-releases"  at "http://repo.typesafe.com/typesafe/releases/",
        "typesafe-snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
      )
  ) ++ SonatypeBuild()
  )
}

object SonatypeBuild {

  import xerial.sbt.Sonatype._

  def apply() = {
    sonatypeSettings ++ Seq(
      pomExtra :=
        <url>https://www.gov.uk/government/organisations/hm-revenue-customs</url>
          <licenses>
            <license>
              <name>Apache 2</name>
              <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
            </license>
          </licenses>
          <scm>
            <connection>scm:git@github.com:hmrc/play-health.git</connection>
            <developerConnection>scm:git@github.com:hmrc/play-health.git</developerConnection>
            <url>git@github.com:hmrc/play-health.git</url>
          </scm>
          <developers>
            <developer>
              <id>charleskubicek</id>
              <name>Charles Kubicek</name>
              <url>http://www.equalexperts.com</url>
            </developer>
          </developers>
    )
  }
}