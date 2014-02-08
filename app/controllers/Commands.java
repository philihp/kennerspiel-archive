package controllers;

import java.awt.image.renderable.RenderableImage;

import models.*;
import models.Command;
import game.*;
import play.*;
import play.data.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Commands extends Controller {
	
	public static Result create() {
		Command command = Form.form(Command.class).bindFromRequest().get();
		System.out.println("INSTANCE: "+command.instance);
		command.save();
		
		return created(Json.toJson(command));
	}
	
	public static Result delete(Integer id) {
		Command.find.byId(id).delete();
		return status(202, "Command deleted");
	}
	
  
}
