package game.agricola2p;

import game.GameError;

public class ActionRSW extends Action {
	
	public ActionRSW(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.activeFarm().reed++;
		board.activeFarm().stone++;
		board.activeFarm().wood++;
	}
	
}
