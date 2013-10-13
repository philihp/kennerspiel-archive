package game.agricola2p;

import game.GameError;
import game.agricola2p.Board.State;

public class ActionWFen extends Action {
	
	public ActionWFen(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.inputState = State.BUILDING_STONE_FENCES;
	}
	
}
