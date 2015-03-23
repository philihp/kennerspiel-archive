package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.instance.*;

@Security.Authenticated(Secured.class)
public class InstanceController extends Controller {

  public static Result create() {
    return ok(create.render(null));
  }
  public static Result join() {
    return ok(join.render(null));
  }

}