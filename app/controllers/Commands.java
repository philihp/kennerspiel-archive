package controllers;

import java.awt.image.renderable.RenderableImage;
import java.util.ArrayList;
import java.util.List;

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
		Command baseCommand = Form.form(Command.class).bindFromRequest().get();
		
		String[] commands = baseCommand.command.split("\n");
		List<Command> commandList = new ArrayList<Command>();
		for(String c : commands) {
			if("".equals(c)) continue;
			Command command = Form.form(Command.class).bindFromRequest().get();
			command.command = c;
			command.save();
			commandList.add(command);
		}
		
		return created(Json.toJson(commandList));
	}
	
	public static Result delete(Integer id) {
		Command.find.byId(id).delete();
		return status(202, "Command deleted");
	}
	
  
}
