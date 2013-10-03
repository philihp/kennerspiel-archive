package game.agricola2p;

public class ActionSP extends Action {
	
	public int wood = 0;
	
	public ActionSP(Board board) {
		super(board);
	}
	
	protected void onNewRound() {
		super.onNewRound();
		this.wood += 1;
	}
	
	protected void onTake() {
		super.onTake();
		board.activeFarm().wood += this.wood;
		this.wood = 0;
		
		board.startingPlayer = board.activeFarm().color;
	}
	
}
