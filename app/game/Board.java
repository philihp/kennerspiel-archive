package game;

import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;

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
	
	public void runCommand(String command) throws GameError, GameWarning {
		throw new UnsupportedOperationException(command);
	}
	
}
