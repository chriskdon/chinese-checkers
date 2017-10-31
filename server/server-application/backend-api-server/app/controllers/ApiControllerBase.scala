package controllers;

import play.api._
import play.api.mvc._
import play.api.libs.json._

import scala.reflect._

import org.codehaus.jackson.map.ObjectMapper

import notifications._

abstract class ApiControllerBase extends Controller {
  private val mapper = new ObjectMapper()
  protected val pushServer = new PushNotificationServer()

  /**
   * Return a JSON String based on the object.
   * @type {[type]}
   */
  protected def okJson(obj:Object):SimpleResult = {
    Ok(mapper.writeValueAsString(obj)).as("application/json")
  }

  /**
   * Parse a JSON request to get the POJO
   * @type {[type]}
   */
  protected def parseRequest[T: ClassTag](request: Request[AnyContent]):T = {
    mapper.readValue(Json.stringify(request.body.asJson.getOrElse(null)), classTag[T].runtimeClass) match {
      case x:T => x
      case _ => throw new ClassCastException
    }
  }


}