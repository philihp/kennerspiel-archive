package controllers;

import java.util.*;
import java.awt.image.renderable.RenderableImage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.*;
import game.*;
import play.*;
import play.data.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Instances extends Controller {

	public static Result getAll(Integer page) {
		if(page == null) page = 1;
		
		JsonNodeFactory factory = JsonNodeFactory.instance;
		
		ArrayNode array = factory.arrayNode();
		
		List<Instance> list = Instance.find.where().findPagingList(5).getPage(page).getList();
		
		for(Instance instance : list) {
			array.add(
					factory.objectNode()
						.put("id", instance.id)
						.put("name", instance.name)
					);
		}
		
		return ok(array);
	}
	
    public static Result get(Integer id, String preview) throws GameError {
    	Instance instance = Instance.find.byId(id);
    	if(instance == null) return notFound("Instance not found!");

    	Board board = instance.getBoard();
		if (preview != null) {
			for (String command : preview.split("\n")) {
				if(command.trim().equals("")) continue;
				board.runCommand(board.getCommand(command));
			}
		}
    	
    	return ok(Json.toJson(instance));
    }
	
	public static Result create() {
		Instance instance = Form.form(Instance.class).bindFromRequest().get();
		instance.save();
		
		return created(Json.toJson(instance));
	}
	
	public static Result delete(Integer id) {
		Instance.find.byId(id).delete();
		return status(202, "Instance deleted");
	}
	
	public static Result update(Integer id) {
		Instance instance = Instance.find.byId(id);
		if(instance == null) return notFound("Instance not found!");
		
		instance = Form.form(Instance.class).bindFromRequest().get();
		instance.update(id);
		
		return status(202, "Instance updated!");
	}
  
  
}
