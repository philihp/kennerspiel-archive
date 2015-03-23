package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.profile.*;

@Security.Authenticated(Secured.class)
public class ProfileController extends Controller {

  public static Result index() {
    User localUser = Application.getLocalUser(session());
    return ok(settings.render(localUser));
  }
}