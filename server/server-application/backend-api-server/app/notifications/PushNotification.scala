package notifications

import models._
import com.ccapi.receivables._
import scala.collection.mutable._

import org.codehaus.jackson.map.ObjectMapper

import play.api.libs.json._
import scala.reflect._

class PushNotification[T <: Receivable : ClassTag] {
  private val mapper = new ObjectMapper()

  private var sendTo:MutableList[User] = new MutableList[User]()
  private var receivable:Option[T] = _

  def this(sendTo:List[User], receivable: Option[T]) = {
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

  def set(receivable: Option[T]):Unit = {
    this.receivable = receivable
  }

  def toJson():String = {
    var regIdList:MutableList[String] = new MutableList[String]()

    for(p <- sendTo) {
      regIdList += p.gcmRegistrationId
    }

    val registrationIds:String = Json.stringify(Json.toJson(regIdList))
    val classname:String = classTag[T].runtimeClass.getCanonicalName()
    val data:String = mapper.writeValueAsString(receivable.getOrElse(null).asInstanceOf[T])

    s"""
      { 
        "registration_ids": $registrationIds,
        "data": {
          "classname": "$classname",
          "receivable": $data
        }
      }
    """
  }
}