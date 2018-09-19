/*
 * Copyright 2016 HM Revenue & Customs
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

import org.scalatest._
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.Await
import scala.language.implicitConversions

class HealthControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  implicit override def newAppForTest(testData: TestData): Application =
    new GuiceApplicationBuilder()
      .configure(testData.configMap ++ Map("play.http.router" -> "health.Routes"))
      .build()

  override def testDataFor(testName: String, theConfigMap: ConfigMap): TestData =
    if ("The details endpoint should respond with a 200 when service is configured with an appName" == testName) {
      super.testDataFor(testName, theConfigMap + ("appName" -> "play-health"))
    } else super.testDataFor(testName, theConfigMap)

  "The Ping endpoint" must {
    "respond with a 200 status code when the service is OK" in {
      status(route(app, FakeRequest("GET", "/ping/ping")).get) must be(200)
    }
  }

  "The details endpoint" should {
    "respond with a 200 when service is configured with an appName" in {
      val res = route(app, FakeRequest("GET", "/admin/details")).get
      status(res) must be(200)
    }

    "respond with a 500 status code when an 'appName' value is not present in the config" in {
      val thrown = intercept[IllegalArgumentException] {
        val res = route(app, FakeRequest("GET", "/admin/details")).get
        Await.result(res, defaultAwaitTimeout.duration)
      }
      thrown.getMessage must be("no config value for key 'appName'")
    }
  }
}
