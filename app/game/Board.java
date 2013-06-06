package game;

import java.util.Random;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;

abstract public class Board {
	
	protected Random rng; 
	
	public void seedRandom(int seed) {
		rng = new Random(seed);
	}
	
	public void changeBoard(String command) {
	}
	
	public JsonNode getState() {
		return Json.newObject();
	}
	
}
