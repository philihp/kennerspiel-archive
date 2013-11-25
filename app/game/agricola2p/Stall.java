package game.agricola2p;

public class Stall extends Buildable {
	
	public Stall(Board board) {
		super(board, "stall");
	}
	
	@Override
	public void returnToStock() {
		board.stalls++;
	}
	
}