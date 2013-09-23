package game.agricola2p;

import java.util.List;

public class Board extends game.Board {
	
	public int round = 0;

	public Action spaceSP = new ActionSP(this);
	public Action space3W = new Action3W(this);
	public Action space1S = new Action1S(this);
	public Action space2S = new Action2S(this);
	public Action spaceWoodFences = new ActionWFen(this);
	public Action spaceStoneFences = new ActionSFen(this);
	public Action spaceRSW = new ActionRSW(this);
	public Action spaceExpansion = new ActionExp(this);

	public Farm redFarm = new Farm(this, "red");
	public Farm blueFarm = new Farm(this, "blue");

	public String startingPlayer = redFarm.color;
	public String currentPlayer;
	
	public Board() {
		onNewRound();
	}

	public void runCommand(String command) {
		System.out.println("Setting Up Board: " + command);
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
		spaceSP.onNewRound();
		space3W.onNewRound();
		space1S.onNewRound();
		space2S.onNewRound();
		spaceWoodFences.onNewRound();
		spaceStoneFences.onNewRound();
		spaceRSW.onNewRound();
		spaceExpansion.onNewRound();
	}
	
}
