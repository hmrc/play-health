resolvers += Resolver.bintrayIvyRepo("hmrc", "sbt-plugin-releases")
resolvers += Resolver.bintrayRepo("hmrc", "releases")
resolvers += Resolver.typesafeRepo("releases")

addSbtPlugin(
  sys.env.get("PLAY_VERSION") match {
    case Some("2.8") => "com.typesafe.play" % "sbt-plugin" % "2.8.7"
    case Some("2.7") => "com.typesafe.play" % "sbt-plugin" % "2.7.4"
    case _           => "com.typesafe.play" % "sbt-plugin" % "2.6.20" // since PlayCrossCompilation.defaultPlayVersion is 2.6
  }
)

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "2.13.0")
addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "2.2.0")
addSbtPlugin("uk.gov.hmrc" % "sbt-artifactory" % "1.13.0")
addSbtPlugin("uk.gov.hmrc" % "sbt-play-cross-compilation" % "2.0.0-SNAPSHOT")
