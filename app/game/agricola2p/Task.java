package game.agricola2p;

/**
 * Each Task is a button that appears above the board. It indicates
 * something that the player can do after they take an action, for example
 * after the player takes the Watering Trough action, they can then click
 * a button that lets them buy additional troughs.
 * @author philihp
 *
 */
abstract class Task {
	
	protected Board board;
	protected String command;
	
	public Task(Board board, String command) {
		this.board = board;
		this.command = command;
	}
	
	abstract boolean usable();

}
