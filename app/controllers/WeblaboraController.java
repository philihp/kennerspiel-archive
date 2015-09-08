package controllers;

import com.avaje.ebean.*;
import com.philihp.weblabora.model.*;
import models.Instance;
import models.State;
import models.User;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.instance.*;

import java.util.List;

public class WeblaboraController extends Controller {

  private static Form<Instance> instanceForm = Form.form(Instance.class);
  private static Form<State> stateForm = Form.form(State.class);

  public static Result get(List<String> command, String proposed) throws WeblaboraException {
    Board board = new Board();

    for(String c : command) {
      MoveProcessor.processMove(board, c);
    }

    if(proposed != null && !"".equals(proposed)) {
      board.preMove(proposed);
      MoveProcessor.processActions(board, proposed);
    }

    return ok(Json.toJson(board));
  }

}
