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

  public static Result create() {
    Form<Instance> form = instanceForm.bindFromRequest();
    return ok(create.render(form));
  }

  public static Result createSubmit() {
    Form<Instance> form = instanceForm.bindFromRequest();
    Instance instance = form.get();
    Ebean.save(instance);
    return redirect(routes.InstanceController.list());
  }

  public static Result list() {
    List<Instance> instances = Ebean.find(Instance.class).findList();
    return ok(list.render(instances));
  }

  public static Result find() {
    return TODO;
  }

}