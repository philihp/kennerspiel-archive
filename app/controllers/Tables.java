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

    public static Result index() {
        return ok(tables.render());
    }
    
    public static Result indexId(Integer id) {
    	return ok(table.render());
    }
  
    public static Result add() {
        Table table = Form.form(Table.class).bindFromRequest().get();
        table.seed = new Random().nextInt();
        table.save();
        return redirect(routes.Tables.index());
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
    	return ok();
    }
    
}
