package uk.gov.hmrc.play.health

import org.scalatest.{FunSpec, ShouldMatchers}
import play.api.libs.ws.WS
import play.api.test.{DefaultAwaitTimeout, FutureAwaits, PlayRunners, TestServer}

class HealthSpec extends FunSpec with ShouldMatchers with PlayRunners with FutureAwaits with DefaultAwaitTimeout {

  describe("Health endpoint") {
    it("should respond with a 200 status code when the service is OK") {
      running(TestServer(3333)) {
        await(WS.url("http://localhost:3333/ping/ping").get).status shouldBe 200
      }
    }

    it("should respond with details") {
      running(TestServer(3333)) {
        println(await(WS.url("http://localhost:3333/ping/details").get).body)
      }
    }
  }
}
