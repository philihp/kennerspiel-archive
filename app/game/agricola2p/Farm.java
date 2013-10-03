package game.agricola2p;

import java.util.ArrayList;
import java.util.List;

public class Farm {
	
	private Board board;
	
	public String color;
	
	public int wood = 0;
	public int stone = 0;
	public int reed = 0;
	public int fences = 9;
	
	public List<Worker> workers = new ArrayList<Worker>();
	
	public Farm(Board board, String color) {
		this.board = board;
		this.color = color;
		workers.add(new Worker(color, false));
		workers.add(new Worker(color, false));
		workers.add(new Worker(color, false));
	}
	
}
