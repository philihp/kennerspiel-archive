package game;

public class GameWarning extends Exception {
	
	private String message;
	
	public GameWarning(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
