package controllers;

import com.avaje.ebean.Ebean;
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
  public static Result find() {
    User user = Application.getLocalUser(session());
    List<Instance> instances =
      Ebean.find(Instance.class)
        .fetch("players")
        .findList();
      // TODO: How do I write the query that says "give me all instances where player is not in"?
    return ok(list.render(instances));
  }

}