package game.agricola2p;

import game.GameError;

public class ActionSP extends Action {
	
	public int wood = 0;
	
	public ActionSP(Board board) {
		super(board);
	}
	
	protected void onNewRound() {
		super.onNewRound();
		this.wood += 1;
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.activeFarm().wood += this.wood;
		this.wood = 0;
		
		board.startingPlayer = board.activeFarm().color;
	}
	
}
