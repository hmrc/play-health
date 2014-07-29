import sbt._
import Keys._

object HmrcBuild extends Build {

  import uk.gov.hmrc.DefaultBuildSettings
  import DefaultBuildSettings._
  import BuildDependencies._
  import uk.gov.hmrc.{SbtBuildInfo, ShellPrompt}
  import play.core.PlayVersion
  import play.PlayImport._

  val nameApp = "play-health"
  val versionApp = "0.3.0"

  val appDependencies = Seq(
    "com.typesafe.play" %% "play" % PlayVersion.current,
    ws % "provided",
    "org.scalatest" %% "scalatest" % "2.2.0" % "test",
    "org.pegdown" % "pegdown" % "1.4.2" % "test"
  )

  lazy val playHealth = Project(nameApp, file("."))
    .enablePlugins(play.PlayScala)
    .settings(version := versionApp)
    .settings(scalaSettings : _*)
    .settings(defaultSettings() : _*)
    .settings(
      targetJvm := "jvm-1.7",
      shellPrompt := ShellPrompt(versionApp),
      libraryDependencies ++= appDependencies,
      resolvers := Seq(
        Opts.resolver.sonatypeReleases,
        Opts.resolver.sonatypeSnapshots,
      "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/",
      "typesafe-snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
      ),
      crossScalaVersions := Seq("2.11.2", "2.10.4")
    )
    .settings(SbtBuildInfo(): _*)
    .settings(SonatypeBuild(): _*)
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