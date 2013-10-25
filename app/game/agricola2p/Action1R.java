package game.agricola2p;

import game.GameError;

public class Action1R extends Action {
	
	public Action1R(Board board) {
		super(board);
	}

	public int reed = 0;
	public int sheep = 0;
	
	protected void onNewRound() {
		super.onNewRound();
		if(this.reed == 1) {
			this.sheep += 1;
		}
		else {
			this.reed = 1;
		}
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.activeFarm().reed += this.reed;
		board.activeFarm().sheep += this.sheep;
		this.reed = 0;
		this.sheep = 0;
	}
	
}
