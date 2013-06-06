package controllers;

import java.util.List;
import java.util.Random;

import models.Command;
import models.Table;
import play.data.*;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Commands extends Controller {
  
    public static Result add() {
    	Command command = Form.form(Command.class).bindFromRequest().get();
    	if(command.command.length() < 5) {
    		return badRequest(Json.toJson(new Object() { public String error = "I didn't like the length";}));
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