package game.agricola2p;

public class Farm {
	
	private Board board;
	
	public String color;
	
	public int workers = 3;
	
	public int wood = 0;
	public int stone = 0;
	public int reed = 0;
	public int fences = 9;
	
	public Farm(Board board, String color) {
		this.board = board;
		this.color = color;
	}
	
}
