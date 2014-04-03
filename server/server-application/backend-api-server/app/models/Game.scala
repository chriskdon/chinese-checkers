package models


import anorm._ 
import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser._
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException

class Game(val gameId:Long) {
  /*
  def getWinner:Long = {
    DB.withConnection { implicit c =>
      SQL("CALL getWinner({gameId}").on("gameId" -> gameId).
    }
  }
  */
}