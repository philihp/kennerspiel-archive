package game.agricola2p;

import game.GameError;

public class Action1Boar extends Action {
	
	public Action1Boar(Board board) {
		super(board);
	}

	public int boar = 0;
	public int sheep = 0;
	
	protected void onNewRound() {
		super.onNewRound();
		if(this.boar == 1) {
			this.sheep += 1;
		}
		else {
			this.boar = 1;
		}
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.activeFarm().boar += this.boar;
		board.activeFarm().sheep += this.sheep;
		this.boar = 0;
		this.sheep = 0;
	}
	
}
