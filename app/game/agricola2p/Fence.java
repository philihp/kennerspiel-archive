package game.agricola2p;

public class Fence extends Buildable {
	
	public Fence(Board board, String type) {
		super(board, "fence", "unfenced");
	}

	@Override
	public boolean getDisabled() {
		//TODO: maybe this should check and see if there are also any unfenced spots... but there probably is anyway
		return board.activeFarm().fences == 0;
	}
	
	@Override
	public void returnToStock() {
		board.activeFarm().fences++;
	}
	
}