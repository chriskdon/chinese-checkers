package controllers

import play.api._
import play.api.mvc._

import com.test._

object Application extends Controller {

  def index = Action {
  	var x = new TestJar();
  	x.test();

    Ok(views.html.index("Your new application is ready."))
  }

}