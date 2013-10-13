package controllers;

import game.Board;
import game.GameError;
import game.GameWarning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;

import models.Command;
import models.Instance;
import models.Response;
import play.data.*;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import views.html.*;
import views.html.defaultpages.error;

public class Instances extends Controller {
  
    public static Result add() {
        Instance instance = Form.form(Instance.class).bindFromRequest().get();
        
        instance.setSeed(new Random().nextInt());
        instance.setGame("agricola2p");
        instance.save();
        
        JsonNode node = Json.toJson(instance);
        return ok(node);
    }
    
    protected static Instance getInstance(Integer id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	Instance instance = Instance.finder.byId(id);
		instance.setBoard((Board)Class.forName("game."+instance.getGame()+".Board").newInstance());
		instance.getBoard().seedRandom(instance.getSeed());
		
		for(Command c : instance.getCommands()) {
			try {
				instance.getBoard().runCommand(c.getCommand());
			}
			catch(GameWarning e) {
				//TODO: alert somehow
			}
			catch(GameError e) {
				//TODO: add error message to instance output
    			break;
			}
		}
		instance.getBoard().preDisplay();

		return instance;
    }
    
    public static Result get(Integer id) throws Exception {
    	try {
    		return ok(Json.toJson(getInstance(id)));
    	}
    	catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
    		throw new Exception(e);
    	}
    }
    
    public static Result getAll() {
    	List<Instance> instances = Instance.finder.all();
    	return ok(Json.toJson(instances));
    }
    
    public static Result delete(Integer id) {
    	Instance table = Instance.finder.byId(id);
    	table.delete();
    	//return noContent();
    	return ok(Json.toJson(table));
    }

    public static Result update(Integer id) {
        Form<Instance> instanceForm = Form.form(Instance.class).bindFromRequest();
        Instance instance = instanceForm.get();
        instance.update();
        return ok(Json.toJson(instance));
    }
    
}
