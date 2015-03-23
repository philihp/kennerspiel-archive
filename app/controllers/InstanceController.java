package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.instance.*;

@Security.Authenticated(Secured.class)
public class InstanceController extends Controller {

  public static Result index() {
    return ok(create.render(null));
  }

}