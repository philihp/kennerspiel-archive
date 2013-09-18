package game.agricola2p;

import java.util.List;

public class Board extends game.Board {
	
	public int round = 0;

	public Space spaceSP = new SpaceSP(this);
	public Space space3W = new Space3W(this);
	public Space space1S = new Space1S(this);
	public Space space2S = new Space2S(this);
	public Space spaceWoodFences = new SpaceWoodFences(this);
	public Space spaceStoneFences = new SpaceStoneFences(this);
	public Space spaceRSW = new SpaceRSW(this);
	public Space spaceExpansion = new SpaceExpansion(this);

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
