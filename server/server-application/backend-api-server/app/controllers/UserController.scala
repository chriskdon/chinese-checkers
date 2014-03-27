package controllers

import play.api._
import play.api.mvc._

import com.ccapp._
import anorm._ 
import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser._
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException


object UserController extends ApiControllerBase {

  def register(username: String) = Action { request =>
    //var user = parseRequest[UserRegistrationResult](request)

    DB.withConnection { implicit c =>
      try {
        val userId: Long = SQL("CALL createUser({username})")
                              .on("username" -> username)
                              .as(scalar[Long].single)

        okJson(new UserRegistrationResult(username, userId))
      } catch {
        case ex: MySQLIntegrityConstraintViolationException => {
          okJson(new ErrorResult(ErrorResult.CODE_GENERIC, "Username Already Exists"))
        }
      }
    } 
  }

  def change(id: Long, username: String) = Action { request =>
    Ok("test")
  }

}