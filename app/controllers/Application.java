package controllers;

import java.util.Date;

import models.*;
import game.Board;
import play.*;
import play.mvc.*;
import play.data.*;
import play.data.validation.Constraints.Required;

import views.html.*;

public class Application extends Controller {

	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(index.render(User.find.byId(request().username())));
	}

	public static Result login() {
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result loginSubmit() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("email", loginForm.get().email);
			return redirect(routes.Application.index());
		}
	}

	public static Result register() {
		return ok(register.render(Form.form(Register.class)));
	}

	public static Result registerSubmit() {
		Form<Register> registerForm = Form.form(Register.class)
				.bindFromRequest();
		if (registerForm.hasErrors()) {
			return badRequest(register.render(registerForm));
		} else {
			new User(registerForm.get().email, registerForm.get().name,
					registerForm.get().password).save();
			session().clear();
			session("email", registerForm.get().email);
			return redirect(routes.Application.index());
		}
	}

	@Security.Authenticated(Secured.class)
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(routes.Application.login());
	}

	@Security.Authenticated(Secured.class)
	public static Result createInstance() {
		System.out.println(User.find.byId(request().username()).name);
		return ok(createInstance.render(Form.form(CreateInstance.class), User.find.byId(request().username())));
	}

	@Security.Authenticated(Secured.class)
	public static Result createInstanceSubmit() {
		Form<CreateInstance> createInstanceForm = Form.form(CreateInstance.class).bindFromRequest();
		if(createInstanceForm.hasErrors()) {
			return badRequest(createInstance.render(createInstanceForm, User.find.byId(request().username())));
		}
		else {
			Instance instance = new Instance();
			instance.seed = (new Date()).getTime();
			instance.name = createInstanceForm.get().name;
			instance.game = createInstanceForm.get().game;
			instance.save();
			return redirect(routes.Application.index());
		}
	}

	public static class Login {
		public String email;
		public String password;

		public String validate() {
			if (User.authenticate(email, password) == null) {
				return "Invalid user or password";
			}
			return null;
		}
	}

	public static class Register {
		public String email;
		public String name;
		public String password;
		public String confirm;

		public String validate() {
			if (!(password != null && password.equals(confirm))) {
				return "Passwords do not match.";
			}
			return null;
		}
	}
	
	public static class CreateInstance {
		
		@Required
		public String name;
		
		@Required
		public String game;
	}
}
