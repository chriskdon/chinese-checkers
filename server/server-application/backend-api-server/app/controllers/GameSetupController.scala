package controllers

import play.api._
import play.api.mvc._

import com.ccapi.receivables._
import com.ccapi._

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
  def join(playerCount: Integer, userId: Long) = Action { request =>
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

        // TODO: Send push notification

        okJson(joinReceivable)
      } catch {
        case ex: Exception => {
          okJson(new ErrorReceivable(ex.getMessage()))
        }
      }
    }
  }

  /**
   * Forfeight from a currently running game.
   * 
   * @type {[type]}
   */
  def forfeight(gameId: Long, userId: Long) = Action { request => 
    Ok("NOT IMPLEMENTED") // TODO
  }

  /**
   * Return the gamestate for a specific game.
   * 
   * @type {[type]}
   */
  def gamestate(gameId: Long) = Action { request =>
    DB.withConnection { implicit c =>
      try {
        val gamestateReceivable:GameStateReceivable = new GameStateReceivable(gameId)

        // Get Players
        val playerResult:List[(Long, String, Int)] = {
          SQL("CALL getGamePlayers({gameId})").on("gameId" -> gameId).as(
            long("userId") ~ str("username") ~ int("playerNumber") map(flatten) *
          ) 
        }

        val players:Array[PlayerInformation] = new Array[PlayerInformation](playerResult.length)

        var i = 0;
        for(p <- playerResult) {
          p match  {
            case (userId, username, playerNumber) => {
             players(i) = new PlayerInformation(userId, username, playerNumber)
            }
          }
          
          i += 1
        }

        gamestateReceivable.players = players;

        // Setup GameState
        var gameStateResult = SQL("CALL getGameState({gameId})").on("gameId" -> gameId).apply().head
        var winnerIdResult = gameStateResult[Option[Long]]("winnerId")
        var winnerId:java.lang.Long = null;

        winnerIdResult match {
          case Some(value) => winnerId = value
          case None => winnerId = null
        }

        val gamestate = new GameState(gameStateResult[Int]("currentTurn"), 
                                      winnerId, gameStateResult[Int]("isReady") == 1,
                                      gameStateResult[Int]("numPlayer"));

        // Setup Pieces
        if(gamestate.isReady) { // Is the game ready?
          var gamePiecesResult:List[(Int, Int, Int)] = {
            SQL("CALL getGamePieces({gameId})").on("gameId" -> gameId).as(
              int("playerNumber") ~ int("onRow") ~ int("onIndex") map(flatten) *
            )
          }

          val pieces:Array[PieceInformation] = new Array[PieceInformation](gamePiecesResult.length)

          i = 0;
          for(p <- gamePiecesResult) {
            p match {
              case (playerNumber, row, index) => {
                pieces(i) = new PieceInformation(playerNumber, row, index)
              }
            }

            i += 1
          }

          gamestate.pieces = pieces;
        }

        gamestateReceivable.gameState = gamestate;

        // Return JSON
        okJson(gamestateReceivable)

      } catch {
        case ex: Exception => okJson(new ErrorReceivable(ex.getMessage()))        
      }
    }
  }
}