package controllers

import play.api._
import play.api.mvc._

import notifications._
import models._
import com.ccapi.receivables._

object Application extends ApiControllerBase {
  def index = Action {
   var p = new PushNotification[ErrorReceivable]()

   p.add(new User(123, "tesd"))
   p.set(Some(new ErrorReceivable("TESTING REFLECTION")))

   System.out.println(pushServer.send(p))


    Ok("Chinese Checkers API Server")
  }
}