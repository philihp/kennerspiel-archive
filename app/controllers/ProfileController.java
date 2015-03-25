package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.profile.*;

@Security.Authenticated(Secured.class)
public class ProfileController extends Controller {

  public static Result settings() {
    User localUser = Application.getLocalUser(session());
    return ok(settings.render(localUser));
  }
  public static Result rankings() {
    User localUser = Application.getLocalUser(session());
    return TODO;
  }
}