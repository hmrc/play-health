resolvers += Resolver.url("hmrc-sbt-plugin-releases", url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(
  Resolver.ivyStylePatterns)

resolvers += Resolver.typesafeRepo("releases")

resolvers += Resolver.bintrayRepo("hmrc", "releases")

val playPlugin =
  if (sys.env.get("PLAY_VERSION").exists(_ == "2.6"))
    "com.typesafe.play" % "sbt-plugin" % "2.6.15"
  else
    "com.typesafe.play" % "sbt-plugin" % "2.5.12"

addSbtPlugin(playPlugin)

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "1.13.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "1.15.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-artifactory" % "0.13.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-play-cross-compilation" % "0.9.0")
