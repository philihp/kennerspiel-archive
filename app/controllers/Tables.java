package controllers;

import game.Board;

import java.util.List;
import java.util.Random;

import org.codehaus.jackson.JsonNode;

import models.Table;
import play.data.*;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Tables extends Controller {
  
    public static Result add() {
        Table table = Form.form(Table.class).bindFromRequest().get();
        table.seed = new Random().nextInt();
        table.game = "agricola2p";
        table.save();
        return ok(Json.toJson(table));
    }
    
    public static Result get(Integer id) {
    	Table table = Table.finder.byId(id);
		try {
			table.board = (Board)Class.forName("game."+table.game+".Board").newInstance();
			table.board.seedRandom(table.seed);
		} catch (ClassNotFoundException e) {
			//do nothing, leave board null
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
    	return ok(Json.toJson(table));
    }
    
    public static Result getAll() {
    	List<Table> tables = Table.finder.all();
    	return ok(Json.toJson(tables));
    }
    
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
    
}
