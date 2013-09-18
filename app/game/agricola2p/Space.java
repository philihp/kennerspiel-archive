package game.agricola2p;

abstract class Space {
	
	protected Board board;
	
	public String occupant = null;
	
	public Space(Board board) {
		this.board = board;
	}
	
	protected void onNewRound() {
		this.occupant = null;
	}
	
	protected void onTake() {
		this.occupant = board.activeFarm().color;
	}
	
}
