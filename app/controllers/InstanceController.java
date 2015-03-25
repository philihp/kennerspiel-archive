package controllers;

import models.Instance;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.instance.*;

@Security.Authenticated(Secured.class)
public class InstanceController extends Controller {

  private static Form<Instance> instanceForm = Form.form(Instance.class);

  public static Result create() {
    Form<Instance> form = instanceForm.bindFromRequest();

    return ok(create.render(form));
  }

  public static Result createSubmit() {
    return ok(create.render(null));
  }
  public static Result join() {
    return TODO;
  }

}