resolvers += Resolver.url("hmrc-sbt-plugin-releases", url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(
  Resolver.ivyStylePatterns)

resolvers += Resolver.typesafeRepo("releases")

resolvers += Resolver.bintrayRepo("hmrc", "releases")

addSbtPlugin(
  sys.env.get("PLAY_VERSION") match {
    case Some("2.6") => "com.typesafe.play" % "sbt-plugin" % "2.6.20"
    case Some("2.7") => "com.typesafe.play" % "sbt-plugin" % "2.7.4"
    case _           => "com.typesafe.play" % "sbt-plugin" % "2.5.19" // since PlayCrossCompilation.defaultPlayVersion is 2.5
  }
)

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "1.13.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "1.15.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-artifactory" % "0.13.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-play-cross-compilation" % "0.19.0")
