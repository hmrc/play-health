package uk.gov.hmrc.play.health

import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.{ShouldMatchers, FunSpec}
import play.api.test._
import play.api.libs.ws.WS

class AdminSpec extends FunSpec with ShouldMatchers with PlayRunners with ScalaFutures with DefaultAwaitTimeout with IntegrationPatience {

  describe("Ping endpoint") {
    it("should respond with a 200 status code when the service is OK") {
      running(TestServer(3333)) {
        WS.url("http://localhost:3333/ping/ping").get.futureValue.status shouldBe 200
      }
    }
  }

  describe("details endpoint") {
    it("should respond with a 200 when service is configured with an appName") {
      running(TestServer(3333, new FakeApplication(additionalConfiguration = Map("appName" -> "play-health")))) {
        WS.url("http://localhost:3333/admin/details").get.futureValue.status shouldBe 200
      }
    }

    it("should respond with a 500 status code when an 'appName' value is not present in the config") {
      running(TestServer(3333, new FakeApplication(additionalConfiguration = Map()))) {
        WS.url("http://localhost:3333/admin/details").get.futureValue.status shouldBe 500
      }
    }
  }

}
