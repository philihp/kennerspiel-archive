package game.agricola2p;

import game.GameError;

public class Action1Cattle extends Action {
	
	public Action1Cattle(Board board) {
		super(board);
	}

	public int cattle = 0;
	public int boar = 0;
	
	protected void onNewRound() {
		super.onNewRound();
		if(this.cattle == 1) {
			this.boar += 1;
		}
		else {
			this.cattle = 1;
		}
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.activeFarm().cattle += this.cattle;
		board.activeFarm().boar += this.boar;
		this.cattle = 0;
		this.boar = 0;
	}
	
}
