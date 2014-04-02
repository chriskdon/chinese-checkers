// @SOURCE:/Users/chriskellendonk/Development/School/COSC3F00/chinese-checkers/server/server-application/backend-api-server/conf/routes
// @HASH:3044d738bf3da4064bd5f1c7f5ca79a3163f5bab
// @DATE:Wed Apr 02 18:10:49 EDT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:19
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:19
class ReverseAssets {
    

// @LINE:19
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:10
// @LINE:9
class ReverseUserController {
    

// @LINE:10
def change(userId:Long, username:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "users/change/" + implicitly[PathBindable[Long]].unbind("userId", userId) + "/" + implicitly[PathBindable[String]].unbind("username", dynamicString(username)))
}
                                                

// @LINE:9
def register(username:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "users/register/" + implicitly[PathBindable[String]].unbind("username", dynamicString(username)))
}
                                                
    
}
                          

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
class ReverseGameSetupController {
    

// @LINE:15
def gamestate(gameId:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "games/gamestate/" + implicitly[PathBindable[Long]].unbind("gameId", gameId))
}
                                                

// @LINE:16
def list(userId:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "games/list/" + implicitly[PathBindable[Long]].unbind("userId", userId))
}
                                                

// @LINE:14
def delete(gameId:Long, userId:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "games/delete/" + implicitly[PathBindable[Long]].unbind("gameId", gameId) + "/" + implicitly[PathBindable[Long]].unbind("userId", userId))
}
                                                

// @LINE:13
def join(playerCount:Integer, userId:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "games/join/" + implicitly[PathBindable[Integer]].unbind("playerCount", playerCount) + "/" + implicitly[PathBindable[Long]].unbind("userId", userId))
}
                                                
    
}
                          

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:19
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:19
class ReverseAssets {
    

// @LINE:19
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:10
// @LINE:9
class ReverseUserController {
    

// @LINE:10
def change : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UserController.change",
   """
      function(userId,username) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/change/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("userId", userId) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("username", encodeURIComponent(username))})
      }
   """
)
                        

// @LINE:9
def register : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UserController.register",
   """
      function(username) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/register/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("username", encodeURIComponent(username))})
      }
   """
)
                        
    
}
              

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
class ReverseGameSetupController {
    

// @LINE:15
def gamestate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GameSetupController.gamestate",
   """
      function(gameId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "games/gamestate/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("gameId", gameId)})
      }
   """
)
                        

// @LINE:16
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GameSetupController.list",
   """
      function(userId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "games/list/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

// @LINE:14
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GameSetupController.delete",
   """
      function(gameId,userId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "games/delete/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("gameId", gameId) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

// @LINE:13
def join : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GameSetupController.join",
   """
      function(playerCount,userId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "games/join/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("playerCount", playerCount) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        
    
}
              

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:19
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:19
class ReverseAssets {
    

// @LINE:19
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:10
// @LINE:9
class ReverseUserController {
    

// @LINE:10
def change(userId:Long, username:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UserController.change(userId, username), HandlerDef(this, "controllers.UserController", "change", Seq(classOf[Long], classOf[String]), "POST", """""", _prefix + """users/change/$userId<[^/]+>/$username<[^/]+>""")
)
                      

// @LINE:9
def register(username:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UserController.register(username), HandlerDef(this, "controllers.UserController", "register", Seq(classOf[String]), "POST", """ User Tasks""", _prefix + """users/register/$username<[^/]+>""")
)
                      
    
}
                          

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
class ReverseGameSetupController {
    

// @LINE:15
def gamestate(gameId:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GameSetupController.gamestate(gameId), HandlerDef(this, "controllers.GameSetupController", "gamestate", Seq(classOf[Long]), "GET", """""", _prefix + """games/gamestate/$gameId<[^/]+>""")
)
                      

// @LINE:16
def list(userId:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GameSetupController.list(userId), HandlerDef(this, "controllers.GameSetupController", "list", Seq(classOf[Long]), "GET", """""", _prefix + """games/list/$userId<[^/]+>""")
)
                      

// @LINE:14
def delete(gameId:Long, userId:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GameSetupController.delete(gameId, userId), HandlerDef(this, "controllers.GameSetupController", "delete", Seq(classOf[Long], classOf[Long]), "POST", """""", _prefix + """games/delete/$gameId<[^/]+>/$userId<[^/]+>""")
)
                      

// @LINE:13
def join(playerCount:Integer, userId:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GameSetupController.join(playerCount, userId), HandlerDef(this, "controllers.GameSetupController", "join", Seq(classOf[Integer], classOf[Long]), "POST", """ Game Setup Tasks""", _prefix + """games/join/$playerCount<[^/]+>/$userId<[^/]+>""")
)
                      
    
}
                          

// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    