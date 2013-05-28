package controllers;

import java.util.List;
import java.util.Random;

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
