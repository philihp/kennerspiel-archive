package controllers;

import com.fasterxml.jackson.databind.node.*;
import com.philihp.weblabora.model.*;
import com.fasterxml.jackson.databind.*;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Kennerspiel.com will be back someday."));
    }

    public static Result weblabora() {
        Board board = new Board(GamePlayers.FOUR, GameLength.LONG, GameCountry.FRANCE);
        return ok(Json.toJson(board));
    }

}
