package notifications

import com.ccapi.receivables._

import play.api.libs.ws._
import scala.concurrent.Future



class PushNotificationServer {

  private implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  def send(notification: PushNotification): Unit = {
    val response = WS.url("https://android.googleapis.com/gcm/send")
                    .withHeaders("Authorization" -> "key=AIzaSyBW_VOWo5A7j-qTd7CZXJvAd6Yt6DhuxNI",
                                 "Content-Type" -> "application/json")
                    .post(notification.toJson)
                    .map { response => 
      
        // TODO: Remove
        System.out.println(notification.toJson)
    }

    response.recover {
      case e: Exception => System.out.println("PUSH ERROR")
    }
  }  
}