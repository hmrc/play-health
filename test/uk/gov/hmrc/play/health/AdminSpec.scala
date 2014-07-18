package uk.gov.hmrc.play.health

import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.{WordSpec, ShouldMatchers, FunSpec}
import play.api.test._
import play.api.libs.ws.{Response, WS}

class AdminSpec extends WordSpec with ShouldMatchers with PlayRunners with ScalaFutures with DefaultAwaitTimeout with IntegrationPatience {

  trait Resource {
    this: WithServer =>
    def resource(path: String) = WS.url(s"http://localhost:$port" + path).get().futureValue
  }

  abstract class ServerWithConfig(conf: Map[String, String] = Map.empty) extends
  WithServer(FakeApplication(additionalConfiguration = conf)) with Resource


  "The Ping endpoint" should {
    "respond with a 200 status code when the service is OK" in new ServerWithConfig {
      resource("/ping/ping").status shouldBe 200
    }
  }

  "The details endpoint" should {
    "respond with a 200 when service is configured with an appName" in
      new ServerWithConfig(Map("appName" -> "play-health")) {
        resource("/admin/details").status shouldBe 200
      }

    "respond with a 500 status code when an 'appName' value is not present in the config" in
      new ServerWithConfig(Map.empty) {
        resource("/admin/details").status shouldBe 500
      }
  }
}
