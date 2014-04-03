package notifications

import models._
import com.ccapi.receivables._
import scala.collection.mutable._

import org.codehaus.jackson.map.ObjectMapper

import play.api.libs.json._

class PushNotification {
  private val mapper = new ObjectMapper()

  private var sendTo:MutableList[User] = new MutableList[User]()
  private var receivable:Option[Receivable] = _

  def this(sendTo:List[User], receivable: Option[Receivable]) = {
    this()

    this.sendTo ++= sendTo
    this.receivable = receivable
  }

  def this(sendTo:List[User]) = {
    this(sendTo, None)
  }

  /**
   * Add a user to the list to send to
   * @type {[type]}
   */
  def add(user:User):Unit = {
    sendTo += user
  }

  def set(receivable: Option[Receivable]):Unit = {
    this.receivable = receivable
  }

  def toJson():String = {
    var regIdList:MutableList[String] = new MutableList[String]()

    for(p <- sendTo) {
      regIdList += p.registrationId
    }

    val registrationIds:String = Json.stringify(Json.toJson(regIdList))
    val data:String = mapper.writeValueAsString(receivable)

    s"""
      { 
        "registration_ids": $registrationIds,
        "data": $data
      }
    """
  }
}