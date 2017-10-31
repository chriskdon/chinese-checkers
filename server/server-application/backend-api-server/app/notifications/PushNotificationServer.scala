package notifications

import com.ccapi.receivables._

import play.api.libs.ws._
import scala.concurrent.Future
import scala.collection.mutable._
import models._
import java.sql._

import anorm._ 
import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser._

class PushNotificationServer {

  private implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  /**
   * Send a push notification to the google servers.
   * @type {[type]}
   */
  def send[T <: Receivable](notification: PushNotification[T]): Unit = {
    val response = WS.url("https://android.googleapis.com/gcm/send")
                    .withHeaders("Authorization" -> "key=AIzaSyBW_VOWo5A7j-qTd7CZXJvAd6Yt6DhuxNI",
                                 "Content-Type" -> "application/json")
                    .post(notification.toJson)
                    .map { response => 
      
        // TODO: Remove
        //System.out.println(response.body)
    }

    response.recover {
      case e: Exception => System.out.println("PUSH ERROR")
    }
  }  

  /**
   * Return the users for a game that have a notification ID
   * @type {[type]}
   */
  def getNotfiableUsers(gameId: Long):List[User] = {
    DB.withConnection { implicit c =>
      val needsNotification = SQL("CALL getGamePlayersNeedNotification({gameId})")
                                  .on("gameId" -> gameId)
                                  .as(int("userId") ~ str("gcmRegistrationId") map(flatten) *)

      // Get users to send to                                    
      var sendTo:MutableList[User] = new MutableList[User]()
      for(u <- needsNotification) {
        u match {
          case (userId, gcmRegistrationId) => {
              sendTo += new User(userId, gcmRegistrationId)
          }
        }
      }  

      sendTo.toList
    }
  }
}