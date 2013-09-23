package game.agricola2p;

public class ActionRSW extends Action {
	
	public ActionRSW(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
	}
	
	protected void onTake() {
		super.onTake();
		board.activeFarm().reed++;
		board.activeFarm().stone++;
		board.activeFarm().wood++;
	}
	
}
