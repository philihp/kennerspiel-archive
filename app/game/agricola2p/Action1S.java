package game.agricola2p;

import game.GameError;

public class Action1S extends Action {
	
	public Action1S(Board board) {
		super(board);
	}

	public int stone = 0;
	
	protected void onNewRound() {
		super.onNewRound();
		this.stone += 3;
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.activeFarm().stone += this.stone;
		this.stone = 0;
	}
	
}
