package game.agricola2p;

abstract class Action {
	
	protected Board board;
	
	public String occupant = null;
	
	public Action(Board board) {
		this.board = board;
	}
	
	protected void onNewRound() {
		this.occupant = null;
	}
	
	protected void onTake() {
		this.occupant = board.activeFarm().color;
		board.activeFarm().workers--;
		board.inputState = "waitingOnCommit";
	}
	
}
