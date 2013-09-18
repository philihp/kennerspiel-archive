package game.agricola2p;

public class Space1S extends Space {
	
	public Space1S(Board board) {
		super(board);
	}

	public int stone = 0;
	
	protected void onNewRound() {
		super.onNewRound();
		this.stone += 3;
	}
	
	protected void onTake() {
		super.onTake();
		board.activeFarm().stone += this.stone;
		this.stone = 0;
	}
	
}
