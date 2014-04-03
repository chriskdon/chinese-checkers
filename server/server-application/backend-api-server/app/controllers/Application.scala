package controllers

import play.api._
import play.api.mvc._

import notifications._
import models._
import com.ccapi.receivables._

object Application extends ApiControllerBase {
  def index = Action {
    Ok("Chinese Checkers API Server")
  }
}