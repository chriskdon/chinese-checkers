package controllers

import play.api._
import play.api.mvc._

import com.ccapi.receivables._
import anorm._ 
import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser._
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException

object GameSetupController extends ApiControllerBase {

  /**
   * Join a new game with a specific number of players. Try and join an existing game, if one does
   * not exist then create a new game.
   * 
   * @type {[type]}
   */
  def join(userId: Long, playerCount: Integer) = Action { request =>
    DB.withConnection { implicit c =>
      try {
        val gameId:Long = SQL("CALL matchMake({userId}, {playerCount})")
                            .on("userId" -> userId, "playerCount" -> playerCount)
                            .as(scalar[Long].single)

        val joinReceivable = new JoinGameReceivable(gameId);

        // Is the game ready -- full of players
       val isReady:Boolean = SQL("CALL isGameReady({gameId})")
                                .on("gameId" -> gameId)
                                .as(scalar[Int].single) == 1;

        joinReceivable.isReady = isReady;                                

        okJson(joinReceivable)
      } catch {
        case ex: Exception => {
          okJson(new ErrorReceivable(ex.getMessage()))
        }
      }
    }
  }
}