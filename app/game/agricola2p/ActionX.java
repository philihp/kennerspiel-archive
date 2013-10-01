package game.agricola2p;

public class ActionX extends Action {

	public int fences = 0;
	
	public ActionX(Board board) {
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
