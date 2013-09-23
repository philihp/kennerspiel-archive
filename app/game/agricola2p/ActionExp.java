package game.agricola2p;

public class ActionExp extends Action {

	public int fences = 0;
	
	public ActionExp(Board board) {
		super(board);
	}
	
	protected void onNewRound() {
		super.onNewRound();
		this.fences += 1;
	}
	
	protected void onTake() {
		super.onTake();
		board.activeFarm().fences += this.fences;
		this.fences = 0;
	}
	
}
