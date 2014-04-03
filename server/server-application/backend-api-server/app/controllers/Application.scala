package controllers

import play.api._
import play.api.mvc._

import notifications._
import models._

object Application extends ApiControllerBase {
  def index = Action {
   var p = new PushNotification()

   p.add(new User(123, "tesd"))

   System.out.println(pushServer.send(p))


    Ok("Chinese Checkers API Server")
  }
}