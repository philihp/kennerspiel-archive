package game.agricola2p;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board extends game.Board {
	
	public int round = 0;

	
	public Map<String, Action> actions = new HashMap<String, Action>();
	{
		actions.put("SP", new ActionSP(this));
		actions.put("Wx3", new Action3W(this));
		actions.put("Sx1", new Action1S(this));
		actions.put("Sx2", new Action2S(this));
		actions.put("WFen", new ActionWFen(this));
		actions.put("SFen", new ActionSFen(this));
		actions.put("RSW", new ActionRSW(this));
		actions.put("X", new ActionX(this));
	}

	public Farm redFarm = new Farm(this, "red");
	public Farm blueFarm = new Farm(this, "blue");

	public String startingPlayer = redFarm.color;
	public String currentPlayer;
	
	public String inputState = "placeWorker";
	
	public Board() {
		onNewRound();
	}

	public void runCommand(String command) {
		switch(command) {
		case "commit" :
			currentPlayer = (currentPlayer.equals(redFarm.color))?blueFarm.color:redFarm.color;
			inputState = "placeWorker";
			if(redFarm.workers == 0 && blueFarm.workers == 0) onNewRound();
			break;
		default :
			Action action = actions.get(command);
			//if(action == null) then error, unknown command
			if(action != null) 
				action.onTake();
		}
	}

	public Farm activeFarm() {
		if (currentPlayer == null) {
			return null;
		}
		else if (redFarm.color.equals(currentPlayer)) {
			return redFarm;
		} else {
			return blueFarm;
		}
	}
	
	public void onNewRound() {
		round++;
		
		currentPlayer = startingPlayer;
		redFarm.workers = 3;
		blueFarm.workers = 3;
		
		for(Action action : actions.values()) {
			action.onNewRound();
		}
		
	}
	
}
