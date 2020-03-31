/*
 * Copyright 2020 HM Revenue & Customs
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

import java.util.jar

import play.api.Environment

import scala.collection.JavaConversions._

trait Manifest {

  def env: Environment

  protected def appName: String

  private lazy val resources = env.resource("META-INF/MANIFEST.MF")

  lazy val contents: Map[String, String] = resources.foldLeft(Map.empty[String, String]) { (map, url) =>
    val manifest = new java.util.jar.Manifest(url.openStream())
    if (map.isEmpty && isApplicationManifest(manifest)) {
      manifest.getMainAttributes.toMap.map { t =>
        t._1.toString -> t._2.toString
      }
    } else {
      map
    }
  }

  private def isApplicationManifest(manifest: jar.Manifest) =
    appName == manifest.getMainAttributes.getValue("Implementation-Title")
}
