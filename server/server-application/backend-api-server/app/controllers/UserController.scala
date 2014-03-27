package controllers

import play.api._
import play.api.mvc._

import com.test._
import org.codehaus.jackson.map.ObjectMapper
import com.ccapp._
import anorm._ 
import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser._
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException


object UserController extends Controller {

  def register(username: String) = Action { request =>
  	var mapper = new ObjectMapper()

	//var user = mapper.readValue(request.body.asText.get, classOf[RegisterUserRequest]);

	DB.withConnection { implicit c =>
		try {
	  		val userId: Long = SQL("CALL createUser({username})")
	  								.on("username" -> username)
	  								.as(scalar[Long].single)

	  		Ok(mapper.writeValueAsString(new UserRegistrationResult(username, userId))).as("application/json")
		} catch {
			case ex: MySQLIntegrityConstraintViolationException => {
				Ok(mapper.writeValueAsString(new ErrorResult(ErrorResult.CODE_GENERIC, "Username Already Exists"))).as("application/json")
			}
		}
	} 
  }

}