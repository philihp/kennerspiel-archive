package controllers;

import game.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;

import models.Command;
import models.Table;
import play.data.*;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import views.html.*;

public class Tables extends Controller {
  
    public static Result add() {
        Table table = Form.form(Table.class).bindFromRequest().get();
        
        table.setSeed(new Random().nextInt());
        table.setGame("agricola2p");
        table.save();
        
        JsonNode node = Json.toJson(table);
        return ok(node);
    }
    
    public static Result get(Integer id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	Table table = Table.finder.byId(id);
		table.setBoard((Board)Class.forName("game."+table.getGame()+".Board").newInstance());
		table.getBoard().seedRandom(table.getSeed());
		
		for(Command c : table.getCommands()) {
			table.getBoard().runCommand(c.getCommand());
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
