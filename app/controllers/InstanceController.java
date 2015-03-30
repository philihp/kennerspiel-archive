package controllers;

import com.avaje.ebean.*;
import models.Instance;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.instance.*;

import java.util.List;

@Security.Authenticated(Secured.class)
public class InstanceController extends Controller {

  private static Form<Instance> instanceForm = Form.form(Instance.class);

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
    Instance instance = Ebean.find(Instance.class, id);
    return ok(get.render(instance));
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
            // let ebean parse the SQL so that it can
            // add expressions to the WHERE and HAVING
            // clauses
            .parse(sql)
                // map resultSet columns to bean properties
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
    flash(Application.FLASH_SUCCESS_KEY, "Successfully joined instance "+instance);
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
    flash(Application.FLASH_SUCCESS_KEY, "Successfully left instance "+instance);
    return redirect(routes.InstanceController.list());
  }

}