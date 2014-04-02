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

        val r = SQL("CALL getGameListItem({gameId}, {userId})")
                            .on("gameId" -> gameId, "userId" -> userId)
                            .apply().head

        val gameListItem = new GameListItem(gameId, r[Int]("isReady") == 1, r[Int]("currentTurn"),
                                            r[Int]("playerNumber"), 
                                            r[Option[String]]("winnerUsername").getOrElse(null).asInstanceOf[String],
                                            r[Option[Int]]("winnerPlayerNumber").getOrElse(null).asInstanceOf[Int],
                                            r[Int]("numPlayer"))

        val joinReceivable = new JoinGameReceivable(gameListItem);

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
   * Delete a game either running or waiting to start.
   * 
   * @type {[type]}
   */
  def delete(gameId: Long, userId: Long) = Action { request => 
    DB.withConnection { implicit c =>
      try {
        val result = SQL("CALL userLeaveGame({userId}, {gameId})")
                            .on("userId" -> userId, "gameId" -> gameId)
                            .apply().head                            

        // Make sure something was updated
        if(result[Option[Long]]("Deleted").isEmpty) {
          throw new Exception("No Game Deleted")
        } else if(result[Int]("HumansLeftInGame") <= 0) {
          // TODO: Handle game full of ai's
          throw new Exception("No Humans In Game")
        }

        okJson(new SuccessReceivable("Game Deleted"))
      } catch {
        case ex: Exception => {
          okJson(new ErrorReceivable(ex.getMessage()))
        }
      }
    }
  }

  /**
   * List all the games that a user is a part of.
   * @type {[type]}
   */
  def list(userId: Long) = Action { request =>
    DB.withConnection { implicit c =>
      try {
        val gameListReceivable:GameListReceivable = new GameListReceivable();

        val listResults = SQL("CALL getGameList({userId})")
                            .on("userId" -> userId)
                            .as(long("gameID") ~ int("numPlayer") ~ int("isReady") ~ int("currentTurn") ~ 
                                get[Int]("playerNumber") ~ get[Option[String]]("winnerUsername") ~ 
                                get[Option[Int]]("winnerPlayerNumber") map(flatten) *)

        var games = new Array[GameListItem](listResults.length)

        var i = 0
        for(p <- listResults) {
          p match {
            case (gameId, numPlayers, isReady, currentTurnNumber, playerNumber, winnerUsername, winnerPlayerNumber) => {
              var game = new GameListItem(gameId, isReady == 1, currentTurnNumber, playerNumber, 
                                          winnerUsername.getOrElse(null).asInstanceOf[String], 
                                          winnerPlayerNumber.getOrElse(null).asInstanceOf[Int],
                                          numPlayers)

              games(i) = game
            }
          }

          i += 1;
        }

        gameListReceivable.gameListItems = games

        // ======================================
        // Submit Result
        // ======================================
        
        okJson(gameListReceivable)
      } catch {
        case ex: Exception => {
          okJson(new ErrorReceivable(ex.getMessage()))
        }
      }
    }
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

        // ======================================
        // Get Players
        // ======================================
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

        // ======================================
        // Setup GameState
        // ======================================
        var gameStateResult = SQL("CALL getGameState({gameId})").on("gameId" -> gameId).apply().head
        var winnerId:java.lang.Long = null;

        winnerId = gameStateResult[Option[Long]]("winnerId").getOrElse(null).asInstanceOf[Long]

        val gamestate = new GameState(gameStateResult[Int]("currentTurn"), 
                                      winnerId, gameStateResult[Int]("isReady") == 1,
                                      gameStateResult[Int]("numPlayer"));

        // ======================================
        // Setup Pieces
        // ======================================
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

        // ======================================
        // Return JSON
        // ======================================
        okJson(gamestateReceivable)

      } catch {
        case ex: Exception => okJson(new ErrorReceivable(ex.getMessage()))        
      }
    }
  }
}