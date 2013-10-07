package controllers;

import java.util.List;
import java.util.Random;

import models.Command;
import models.Response;
import models.Instance;
import play.data.*;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Commands extends Controller {
  
    public static Result add() {
    	Command command = Form.form(Command.class).bindFromRequest().get();
    	if(command.getCommand() == null || command.getCommand().length() == 0) {
    		return badRequest(Response.json("Missing Command"));
    	}
    	else if(command.getCommand().equals("undo")) {
    		Instance instance = Instance.finder.byId(command.getInstance().getId());
    		if(instance == null)
    			return badRequest(Response.json("No such Table ID "+command.getInstance().getId()));
    		
    		List<Command> commands = instance.getCommands();
    		if(commands.size() == 0) {
    			return badRequest(Response.json("Instance "+command.getInstance().getId()+" has no moves to undo."));
    		}
    		
    		Command c = commands.get(commands.size()-1);
    		if("commit".equals(c.getCommand())) {
    			return badRequest(Response.json("You can't undo a commit."));
    		}
    		
    		c.delete();
    		
    		return ok(Response.json("Last move undone."));
    	}
    	else {
	    	command.save();
	        return ok(Json.toJson(command));
    	}
    }
    
    public static Result get(Integer id) {
    	Command command = Command.finder.byId(id);
    	return ok(Json.toJson(command));
    }

    /*
    public static Result delete(Integer id) {
    	Table table = Table.finder.byId(id);
    	table.delete();
    	//return noContent();
    	return ok(Json.toJson(table));
    }

    public static Result update(Integer id) {
        Form<Table> tableForm = Form.form(Table.class).bindFromRequest();
        Table table = tableForm.get();
        table.update();
        return ok(Json.toJson(table));

    }
    */
    
}
