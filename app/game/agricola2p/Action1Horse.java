package game.agricola2p;

import game.GameError;

public class Action1Horse extends Action {
	
	public Action1Horse(Board board) {
		super(board);
	}

	public int horses = 0;
	public int sheep = 0;
	
	protected void onNewRound() {
		super.onNewRound();
		if(this.horses == 1) {
			this.sheep += 1;
		}
		else {
			this.horses = 1;
		}
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.activeFarm().horses += this.horses;
		board.activeFarm().sheep += this.sheep;
		this.horses = 0;
		this.sheep = 0;
	}
	
}
