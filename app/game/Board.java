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
	
	/**
	 * Do various things before displaying that we normally don't want
	 * to do between every turn.
	 * 
	 * For example, determine which actions are disabled and which are
	 * usable by the player. This could be time-consuming, and we don't
	 * really care to do this when playing back moves already made,
	 * because they've already been made. 
	 */
	public void preDisplay() {
		
	}
	
}
