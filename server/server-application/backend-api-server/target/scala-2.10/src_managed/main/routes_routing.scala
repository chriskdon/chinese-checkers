// @SOURCE:/Users/kubasub/Dropbox/Code/chinese-checkers/server/server-application/backend-api-server/conf/routes
// @HASH:337a9641dfe0b1bcfdc7e699b0f92e5115ac14cd
// @DATE:Wed Apr 02 16:36:46 EDT 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_UserController_register1 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/register/"),DynamicPart("username", """[^/]+""",true))))
        

// @LINE:10
private[this] lazy val controllers_UserController_change2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/change/"),DynamicPart("userId", """[^/]+""",true),StaticPart("/"),DynamicPart("username", """[^/]+""",true))))
        

// @LINE:13
private[this] lazy val controllers_GameSetupController_join3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("games/join/"),DynamicPart("playerCount", """[^/]+""",true),StaticPart("/"),DynamicPart("userId", """[^/]+""",true))))
        

// @LINE:14
private[this] lazy val controllers_GameSetupController_delete4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("games/delete/"),DynamicPart("gameId", """[^/]+""",true),StaticPart("/"),DynamicPart("userId", """[^/]+""",true))))
        

// @LINE:15
private[this] lazy val controllers_GameSetupController_gamestate5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("games/"),DynamicPart("gameId", """[^/]+""",true),StaticPart("/gamestate"))))
        

// @LINE:18
private[this] lazy val controllers_Assets_at6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/register/$username<[^/]+>""","""controllers.UserController.register(username:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/change/$userId<[^/]+>/$username<[^/]+>""","""controllers.UserController.change(userId:Long, username:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """games/join/$playerCount<[^/]+>/$userId<[^/]+>""","""controllers.GameSetupController.join(playerCount:Integer, userId:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """games/delete/$gameId<[^/]+>/$userId<[^/]+>""","""controllers.GameSetupController.delete(gameId:Long, userId:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """games/$gameId<[^/]+>/gamestate""","""controllers.GameSetupController.gamestate(gameId:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_UserController_register1(params) => {
   call(params.fromPath[String]("username", None)) { (username) =>
        invokeHandler(controllers.UserController.register(username), HandlerDef(this, "controllers.UserController", "register", Seq(classOf[String]),"POST", """ User Tasks""", Routes.prefix + """users/register/$username<[^/]+>"""))
   }
}
        

// @LINE:10
case controllers_UserController_change2(params) => {
   call(params.fromPath[Long]("userId", None), params.fromPath[String]("username", None)) { (userId, username) =>
        invokeHandler(controllers.UserController.change(userId, username), HandlerDef(this, "controllers.UserController", "change", Seq(classOf[Long], classOf[String]),"POST", """""", Routes.prefix + """users/change/$userId<[^/]+>/$username<[^/]+>"""))
   }
}
        

// @LINE:13
case controllers_GameSetupController_join3(params) => {
   call(params.fromPath[Integer]("playerCount", None), params.fromPath[Long]("userId", None)) { (playerCount, userId) =>
        invokeHandler(controllers.GameSetupController.join(playerCount, userId), HandlerDef(this, "controllers.GameSetupController", "join", Seq(classOf[Integer], classOf[Long]),"POST", """ Game Setup Tasks""", Routes.prefix + """games/join/$playerCount<[^/]+>/$userId<[^/]+>"""))
   }
}
        

// @LINE:14
case controllers_GameSetupController_delete4(params) => {
   call(params.fromPath[Long]("gameId", None), params.fromPath[Long]("userId", None)) { (gameId, userId) =>
        invokeHandler(controllers.GameSetupController.delete(gameId, userId), HandlerDef(this, "controllers.GameSetupController", "delete", Seq(classOf[Long], classOf[Long]),"POST", """""", Routes.prefix + """games/delete/$gameId<[^/]+>/$userId<[^/]+>"""))
   }
}
        

// @LINE:15
case controllers_GameSetupController_gamestate5(params) => {
   call(params.fromPath[Long]("gameId", None)) { (gameId) =>
        invokeHandler(controllers.GameSetupController.gamestate(gameId), HandlerDef(this, "controllers.GameSetupController", "gamestate", Seq(classOf[Long]),"GET", """""", Routes.prefix + """games/$gameId<[^/]+>/gamestate"""))
   }
}
        

// @LINE:18
case controllers_Assets_at6(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     