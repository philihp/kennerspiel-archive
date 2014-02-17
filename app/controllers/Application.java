package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
		User user = User.find.byId(request().username());
		List<Instance> instances = new ArrayList<Instance>(user.instances.size());
		instances.addAll(user.instances);
		
		return ok(index.render(user, instances));
	}

	@Security.Authenticated(Secured.class)
	public static Result join() {
		User user = User.find.byId(request().username());
		return ok(join.render(user));
	}

	@Security.Authenticated(Secured.class)
	public static Result settings() {
		User user = User.find.byId(request().username());
		return ok(settings.render(user));
	}

	@Security.Authenticated(Secured.class)
	public static Result stats() {
		User user = User.find.byId(request().username());
		return ok(stats.render(user));
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
	public static Result create() {
		Form<Create> createForm = Form.form(Create.class);
		User user = User.find.byId(request().username());
		return ok(create.render(createForm, user));
	}

	@Security.Authenticated(Secured.class)
	public static Result createSubmit() {
		Form<Create> createForm = Form.form(Create.class).bindFromRequest();
		User user = User.find.byId(request().username());
		if(createForm.hasErrors()) {
			return badRequest(create.render(createForm, user));
		}
		else {
			Instance instance = new Instance();
			instance.seed = (new Date()).getTime();
			instance.name = createForm.get().name;
			instance.game = createForm.get().game;
			instance.users.add(User.find.byId(request().username()));
			instance.save();
			return redirect(routes.Application.index());
		}
	}

	@Security.Authenticated(Secured.class)
	public static Result instance(Integer id) {
		User user = User.find.byId(request().username());
		Instance ins = Instance.find.byId(id);
		
		return ok(instance.render(user, ins));
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
	
	public static class Create {
		
		@Required
		public String name;
		
		@Required
		public String game;
	}
}
