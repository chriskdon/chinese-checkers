package controllers

import play.api._
import play.api.mvc._

import com.ccapi.postdata._
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
  def register() = Action { request =>
    val userPostData = parseRequest[RegisterUserPostData](request)

    DB.withConnection { implicit c =>
      try {
        val userId: Long = SQL("CALL createUser({username}, {gcmRegistrationId})")
                              .on("username" -> userPostData.username, 
                                  "gcmRegistrationId" -> userPostData.gcmRegistrationId)
                              .as(scalar[Long].single)

        okJson(new UserRegistrationReceivable(userPostData.username, userId))
      } catch {
        case ex: MySQLIntegrityConstraintViolationException => {
          okJson(new ErrorReceivable(ex.getMessage()))
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