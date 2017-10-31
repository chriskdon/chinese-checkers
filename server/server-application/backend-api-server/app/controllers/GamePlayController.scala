package controllers

import play.api._
import play.api.mvc._

import notifications._
import models._

import com.ccapi._
import com.ccapi.receivables._
import com.ccapi.postdata._

import anorm._ 
import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser._


object GamePlayController extends ApiControllerBase {
  def move() = Action { request =>
    val movePostData = parseRequest[PlayerMovePostData](request)

    DB.withConnection { implicit c =>
      try {
        val count = SQL("CALL updatePiece({gameId}, {userId}, {oldRow}, {oldIndex}, {newRow}, {newIndex})")
                      .on("gameId" -> movePostData.gameId, 
                          "userId" -> movePostData.userId,
                          "oldRow" -> movePostData.move.startRow,
                          "oldIndex" -> movePostData.move.startIndex,
                          "newRow" -> movePostData.move.endRow,
                          "newIndex" -> movePostData.move.endIndex)
                      .executeUpdate()

        if(count <= 0) {
          throw new Exception("Move could not be made.")
        }

        if(new Game(movePostData.gameId).hasWinner) { // Has Winner
          val username = SQL("CALL getUsername({userId})")
                .on("userId" -> movePostData.userId)
                .as(scalar[String].single)

          val playerNumber = SQL("SELECT playerNumber FROM gamesUsers WHERE gameId = {gameId} AND userId = {userId}")
                              .on("gameId" -> movePostData.gameId, "userId" -> movePostData.userId)
                              .as(scalar[Int].single)

          val gameOver = new GameOverNotificationReceivable(movePostData.gameId, movePostData.userId, playerNumber, 
                                                            username)

          var g = new PushNotification[GameOverNotificationReceivable](pushServer.getNotfiableUsers(movePostData.gameId), 
                                                            Some(gameOver))

          pushServer.send(g)
        } else { // Next Turn
          SQL("CALL nextTurn({gameId})").on("gameId" -> movePostData.gameId).execute()
        }

        // Send Move Push to players indicating move was made
        val playerMoveRecievable = new PlayerMoveReceivable(movePostData.gameId, movePostData.userId, 
                                                            null, 
                                                            movePostData.move)
        

        var p = new PushNotification[PlayerMoveReceivable](pushServer.getNotfiableUsers(movePostData.gameId), 
                                                            Some(playerMoveRecievable))

        pushServer.send(p)
        
        // Result
        okJson(new SuccessReceivable("Move Received"))
      } catch {
        case ex: Exception => {
          okJson(new ErrorReceivable(ex.getMessage()))
        }
      }
    } 
  }
}