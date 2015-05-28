/*
 * Copyright 2015 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

  val testConfig = Map("application.router" -> "health.Routes")

  abstract class ServerWithConfig(conf: Map[String, String] = Map.empty) extends
    WithServer(FakeApplication(additionalConfiguration = testConfig ++ conf)) with Resource

  "The Ping endpoint" should {
    "respond with a 200 status code when the service is OK" in new ServerWithConfig(Map("application.router" -> "health.Routes")) {
      resource("/ping/ping").status shouldBe 200
    }
  }

  "The details endpoint" should {
    "respond with a 200 when service is configured with an appName" in
      new ServerWithConfig(Map("appName" -> "play-health", "application.router" -> "health.Routes")) {
        resource("/admin/details").status shouldBe 200
      }

    "respond with a 500 status code when an 'appName' value is not present in the config" in
      new ServerWithConfig(Map("application.router" -> "health.Routes")) {
        resource("/admin/details").status shouldBe 500
      }
  }
}
