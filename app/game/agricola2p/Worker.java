package game.agricola2p;

public class Worker {
	
	private String color;
	private boolean usable;

	public Worker(String color, boolean usable) {
		this.color = color;
		this.usable = usable;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isUsable() {
		return usable;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}
}
