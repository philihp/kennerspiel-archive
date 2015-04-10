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

import java.util.Comparator;
import java.util.List;

@Security.Authenticated(Secured.class)
public class InstanceController extends Controller {

  private static Form<Instance> instanceForm = Form.form(Instance.class);
  private static Form<State> stateForm = Form.form(State.class);

  /**
   * Displays a form for creating an instance
   */
  public static Result create() {
    Form<Instance> form = instanceForm.bindFromRequest();
    return ok(create.render(form));
  }

  /**
   * Creates an instance
   */
  public static Result createSubmit() {
    User user = Application.getLocalUser(session());
    Form<Instance> form = instanceForm.bindFromRequest();
    Instance instance = form.get();
    instance.players.add(user);
    instance.phase = Instance.Phase.NEW;
    Ebean.save(instance);
    return redirect(routes.InstanceController.list());
  }

  /**
   * Lists all of the current user's instances
   */
  public static Result list() {
    User user = Application.getLocalUser(session());
    List<Instance> instances = user.instances;
    //Ebean.find(Instance.class).where().eq("players", localUser).findList();
    return ok(list.render(instances));
  }

  /**
   * Shows an instance
   */
  public static Result get(Long id) {
    Instance instance = Ebean.find(Instance.class).fetch("states").where().idEq(id).findUnique();
    instance.sortStates();
    return ok(get.render(instance, stateForm));
  }

  public static Result getJSON(Long id) throws WeblaboraException {
    Board board = new Board(GamePlayers.FOUR, GameLength.LONG, GameCountry.FRANCE);
    Instance instance = Ebean.find(Instance.class).fetch("states").where().idEq(id).findUnique();
    for(State state : instance.states) {
      MoveProcessor.processMove(board, state.token);
    }
    return ok(Json.toJson(board));
  }

  /**
   * Shows instances that the user can join
   */
  public static Result join() {
//    String sql = "SELECT i.id, i.game_name FROM instance i"
//                +"  LEFT JOIN instance_users x ON (i.id = x.instance_id AND x.users_id = :user_id)"
//                +"  WHERE x.instance_id IS NULL";

    //would be neat figure out how to do this without rawSql
    String sql = "SELECT i.id, i.game_name FROM instance i"
                +"  WHERE NOT EXISTS(SELECT * FROM instance_users x WHERE i.id = x.instance_id AND x.users_id = :user_id)";
    User user = Application.getLocalUser(session());
    RawSql rawSql =
        RawSqlBuilder
            .parse(sql)
            .columnMapping("i.id", "id")
            .columnMapping("i.game_name", "gameName")
            .create();
    Query<Instance> query = Ebean.find(Instance.class);
    List<Instance> instances = query
        .setRawSql(rawSql)
        .setParameter("user_id", user.id)
        .where().eq("phase", Instance.Phase.NEW).findList();
    return ok(join.render(instances));
  }

  /**
   * Joins the current user to the instance.
   */
  public static Result joinSubmit(Long id) {
    Instance instance = Ebean.find(Instance.class, id);
    User user = Application.getLocalUser(session());
    instance.players.add(user);
    Ebean.save(instance);
    flash(Application.FLASH_SUCCESS_KEY, "Successfully joined instance " + instance);
    return redirect(routes.InstanceController.get(id));
  }

  /**
   * Removes the current user from the instance.
   */
  public static Result leaveSubmit(Long id) {
    Instance instance = Ebean.find(Instance.class, id);
    User user = Application.getLocalUser(session());
    instance.players.remove(user);
    Ebean.save(instance);
    flash(Application.FLASH_SUCCESS_KEY, "Successfully left instance " + instance);
    return redirect(routes.InstanceController.list());
  }

  /**
   * Adds a new state to an instance
   */
  public static Result explore(Long instance_id) {
    User user = Application.getLocalUser(session());
    Instance instance = Ebean.find(Instance.class, instance_id);
    Form<State> form = stateForm.bindFromRequest();
    State newState = form.get();

    try {
      Board board = new Board(GamePlayers.FOUR, GameLength.LONG, GameCountry.FRANCE);
      instance.sortStates();
      for(State state : instance.states) {
        MoveProcessor.processMove(board, state.token);
      }
      MoveProcessor.processMove(board, newState.token);
      newState.instance = instance;
      Ebean.save(newState);
    }
    catch(WeblaboraException e) {
      flash(Application.FLASH_DANGER_KEY, e.getMessage());
    }
    return redirect(routes.InstanceController.get(instance_id));
  }

  /**
   * Removes the last explored state
   */
  public static Result backtrack(Long id) {
    Instance instance = Ebean.find(Instance.class, id);
    if(instance.states.isEmpty()) {
      flash(Application.FLASH_DANGER_KEY, "You can not backtrack past the start of the game");
    }
    else {
      instance.sortStates();
      State lastState = instance.states.get(instance.states.size()-1);
      Ebean.delete(lastState);
    }
    return redirect(routes.InstanceController.get(id));
  }

}