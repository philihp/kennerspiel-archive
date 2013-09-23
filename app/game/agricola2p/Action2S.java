package game.agricola2p;

public class Action2S extends Action {

	public int stone = 0;
	
	public Action2S(Board board) {
		super(board);
	}
	
	protected void onNewRound() {
		super.onNewRound();
		this.stone += 2;
	}
	
	protected void onTake() {
		super.onTake();
		board.activeFarm().stone += this.stone;
		this.stone = 0;
	}
	
}
