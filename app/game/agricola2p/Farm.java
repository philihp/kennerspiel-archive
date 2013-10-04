package game.agricola2p;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;

public class Farm {
	
	private Board board;
	
	public String color;
	
	public int wood = 0;
	public int stone = 0;
	public int reed = 0;
	public int fences = 9;
	
	private static Set<Integer> ROWS = Ranges.closed(0, 6).asSet(DiscreteDomains.integers());
	
	public ArrayTable<Integer, Integer, Feature> terrain = ArrayTable.create(, 5); 
	
	public List<Worker> workers = new ArrayList<Worker>();
	
	public Farm(Board board, String color) {
		this.board = board;
		this.color = color;
		workers.add(new Worker(color, false));
		workers.add(new Worker(color, false));
		workers.add(new Worker(color, false));
	}
	
}
