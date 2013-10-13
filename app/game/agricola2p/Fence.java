package game.agricola2p;

public class Fence extends Buildable {
	
	public Fence(Board board) {
		super(board, "fence");
	}

	@Override
	public boolean getDisabled() {
		return board.troughs == 0;
	}
	
	@Override
	public void returnToStock() {
		board.troughs++;
	}
	
}