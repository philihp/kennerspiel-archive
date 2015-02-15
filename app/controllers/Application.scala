package controllers

import com.philihp.weblabora.model._
import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Kennerspiel.com will be coming back someday soon, maybe."))
  }

  def weblabora = Action {
    var board :Board = new Board(GamePlayers.FOUR, GameLength.SHORT, GameCountry.FRANCE)
    Ok(board.toString())
  }

}