package models


import anorm._ 
import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser._
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException

class Game(val gameId:Long) {
  
  def hasWinner:Boolean = {
    DB.withConnection { implicit c =>
      val hasWinner = SQL("""
        SELECT
          (SELECT COUNT(*) FROM (
            SELECT COUNT(*) 
            FROM (SELECT P.*, GU.playerNumber, L.playerNumber AS inGoal
                FROM pieces P
                  LEFT JOIN gamesusers GU ON GU.guID = P.guID
                  LEFT JOIN lookuptable L ON L.playerNumber = GU.playerNumber AND L.onRow = P.onRow AND L.onIndex = P.onIndex
                WHERE GU.gameId = {gameId}) X
            WHERE inGoal IS NULL
            GROUP BY playerNumber
            HAVING COUNT(*) >= 1) Y) - (SELECT numPlayer FROM games WHERE gameId = {gameId}) AS X;
        """).on("gameId" -> gameId).as(scalar[Long].single) != 0

      return hasWinner
    }
  }
  
}