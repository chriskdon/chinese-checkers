package controllers;

import play.api._
import play.api.mvc._

import scala.reflect._

import org.codehaus.jackson.map.ObjectMapper

abstract class ApiControllerBase extends Controller {
  protected val mapper = new ObjectMapper()

  /**
   * Return a JSON String based on the object.
   * @type {[type]}
   */
  protected def okJson(obj:Object):SimpleResult = {
    Ok(mapper.writeValueAsString(obj)).as("application/json")
  }

  protected def parseRequest[T: ClassTag](request: Request[AnyContent]):T = {
    mapper.readValue(request.body.asText.get, classTag[T].runtimeClass) match {
      case x:T => x
      case _ => throw new ClassCastException
    }
  }


}