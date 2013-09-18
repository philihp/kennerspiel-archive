package game.agricola2p;

public class Space2S extends Space {

	public int stone = 0;
	
	public Space2S(Board board) {
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
