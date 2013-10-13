package game;

public class GameError extends Exception {
	
	private String message;
	
	public GameError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
