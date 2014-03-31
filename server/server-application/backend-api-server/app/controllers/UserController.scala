package controllers

import play.api._
import play.api.mvc._

import com.ccapi.receivables._
import anorm._ 
import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser._
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException

object UserController extends ApiControllerBase {

  /**
   * Register a new user.
   * 
   * @type {[type]}
   */
  def register(username: String) = Action { request =>
    //var user = parseRequest[UserRegistrationResult](request)

    DB.withConnection { implicit c =>
      try {
        val userId: Long = SQL("CALL createUser({username})")
                              .on("username" -> username)
                              .as(scalar[Long].single)

        okJson(new UserRegistrationReceivable(username, userId))
      } catch {
        case ex: MySQLIntegrityConstraintViolationException => {
          okJson(new ErrorReceivable("Username Already Exists"))
        }
      }
    } 
  }

  /**
   * Change an already registered user's username.
   * 
   * @type {[type]}
   */
  def change(userId: Long, username: String) = Action { request =>
    DB.withConnection { implicit c =>
      try {
        SQL("CALL setUsername({userId}, {username})")
            .on("userId" -> userId, "username" -> username)
            .execute()

        okJson(new SuccessReceivable())
      } catch {
        case ex: MySQLIntegrityConstraintViolationException => {
          okJson(new ErrorReceivable("Username Already Exists"))
        }
      }
    }
  }

}