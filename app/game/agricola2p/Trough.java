package game.agricola2p;

public class Trough extends Buildable {
	
	public Trough(Board board) {
		super(board, "trough");
	}
	
	@Override
	public void returnToStock() {
		board.troughs++;
	}
	
}