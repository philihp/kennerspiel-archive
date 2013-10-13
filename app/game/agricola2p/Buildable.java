package game.agricola2p;

/**
 * Each Task is a button that appears above the board. It indicates
 * something that the player can do after they take an action, for example
 * after the player takes the Watering Trough action, they can then click
 * a button that lets them buy additional troughs.
 * @author philihp
 *
 */
abstract class Buildable {
	
	protected Board board;
	
	public String target;
	public String type;
	
	public Buildable(Board board, String type, String target) {
		this.board = board;
		this.type = type;
		this.target = target;
	}
	
	public boolean getDisabled() {
		return false;
	}

	/**
	 * This buildable was created, ready to be built, but the player
	 * decided to end their turn. (Basically this is just used for
	 * fences)
	 */
	public void returnToStock() {
		
	}

}
