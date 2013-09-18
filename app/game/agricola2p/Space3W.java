package game.agricola2p;

public class Space3W extends Space {

	public int wood = 0;
	
	public Space3W(Board board) {
		super(board);
	}
	
	protected void onNewRound() {
		super.onNewRound();
		this.wood += 3;
	}
	
	protected void onTake() {
		super.onTake();
		board.activeFarm().wood += this.wood;
		this.wood = 0;
	}
	
}
