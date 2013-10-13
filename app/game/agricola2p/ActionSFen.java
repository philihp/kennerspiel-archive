package game.agricola2p;

import game.GameError;
import game.agricola2p.Board.State;

public class ActionSFen extends Action {
	
	public ActionSFen(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.inputState = State.BUILDING_STONE_FENCES;
		board.buildableFences += 2;
	}

	protected void onTake(String[] params) throws GameError {
		if(params[1].equals("Buy") == false) {
			throw new GameError("Unknown Command "+params[1]);
		}
		else if(board.activeFarm().stone < 2) {
			throw new GameError("Not enough stone to build a fence");
		}
		else {
			board.activeFarm().stone -= 2;
			board.buildableFences += 1;
		}
	}
}
