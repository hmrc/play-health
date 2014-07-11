package uk.gov.hmrc.play.health

import play.api.Play
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object HealthController extends HealthController{

  import play.api.Play.current

  def service = new HealthService(){
    val vendor = Play.configuration.getString("health.vendor").getOrElse{
      throw new IllegalArgumentException("no config value for key 'health.vendor'")
    }
  }

}

trait HealthController extends Controller {

  protected def service:HealthService

  def ping = Action {
    Ok
  }

  def details() = Action {
    Ok(Json.toJson(service.manifest))
  }

  def detail(name: String) = Action {
    service.manifest.get(name) match {
      case Some(m) => Ok(m)
      case None    => NotFound
    }
  }
}
