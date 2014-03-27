package controllers

import play.api._
import play.api.mvc._

import com.test._
import org.codehaus.jackson.map.ObjectMapper;
import com.ccapp._;

object Application extends Controller {

  def index = Action {
  	var x = new TestJar()
  	x.test()

  	var mapper = new ObjectMapper()
  	var y = new RegisterUserRequest();

    Ok(mapper.writeValueAsString(y))
  }

}