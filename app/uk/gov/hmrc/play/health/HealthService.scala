package uk.gov.hmrc.play.health

import play.api.Play
import collection.JavaConversions._

trait HealthService {

  import play.api.Play.current

  protected def vendor:String

  lazy val manifest: Map[String, String] = resources.foldLeft(Map.empty[String, String]) { (map, url) =>
    val manifest = new java.util.jar.Manifest(url.openStream())
    if (map.isEmpty && isApplicationManifest(manifest)) {
      manifest.getMainAttributes.toMap.map {
        t => t._1.toString -> t._2.toString
      }
    } else {
      map
    }
  }

  private val resources = Play.application.classloader.getResources("META-INF/MANIFEST.MF")

  private def isApplicationManifest(manifest: java.util.jar.Manifest) = {
    vendor.equals(manifest.getMainAttributes.getValue("Implementation-Vendor"))
  }
}
