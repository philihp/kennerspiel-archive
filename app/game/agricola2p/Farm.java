package game.agricola2p;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Range;

public class Farm {
	
	private Board board;
	
	public String color;
	
	public int wood = 0;
	public int stone = 0;
	public int reed = 0;
	public int fences = 9;
	
	protected int rowRangeMin = 0;
	protected int rowRangeMax = 6;
	
	protected int colRangeMin = 8;
	protected int colRangeMax = 12;
	
	protected ArrayTable<Integer, Integer, Lot> terrain = createTerrainTable();
	
	protected Set<Integer> getRowRange() {
		return ContiguousSet.create(Range.closed(rowRangeMin, rowRangeMax), 
				DiscreteDomain.integers());
	}
	protected Set<Integer> getColRange() {
		return ContiguousSet.create(Range.closed(colRangeMin, colRangeMax), 
				DiscreteDomain.integers());
	}
	
	protected ArrayTable<Integer, Integer, Lot> createTerrainTable() {
		return ArrayTable.create(getRowRange(),getColRange());
	}
	
	public List<Worker> workers = new ArrayList<Worker>();
	
	public Farm(Board board, String color) {
		this.board = board;
		this.color = color;
		workers.add(new Worker(color, false));
		workers.add(new Worker(color, false));
		workers.add(new Worker(color, false));
		for(Integer y : getRowRange()) {
			for(Integer x : getColRange()) {
				Lot lot = null;
				if(y % 2 == 0 && x % 2 == 0) {
					lot = new LotNull(x,y);
				}
				else if(y % 2 == 1 && x % 2 == 1) {
					lot = new LotPasture(x,y);
				}
				else {
					lot = new LotFence(x,y);
				}
				terrain.put(y, x, lot);
			}
		}
		((LotFence)terrain.get(4, 9)).built = true;
		((LotFence)terrain.get(5, 8)).built = true;
		((LotPasture)terrain.get(5, 9)).building = "cottage";
		((LotFence)terrain.get(5, 10)).built = true;
		((LotFence)terrain.get(6, 9)).built = true;
	}
	
	public Lot[][] getTerrain() {
		return terrain.toArray(Lot.class);
	}
	
	public boolean isActive() {
		return board.activeFarm().equals(this);
	}
	
	/**
	 * Compute the "enclosed" property of every pasture.
	 * 
	 * #1 First, turn them all to true.
	 * #2 Go along the north, then west, then south, then east borders, and if there's
	 *    no fence there, then set that bordering pasture to enclosed = false.
	 * #3 For every enclosed pasture = false, scan its neighbors. If not blocked by a
	 *    fence, then set that neighbor to false, and set "didAnythingChange" to true.
	 * #4 Repeat #3 until didAnythingChange is false. 
	 * 
	 */
	protected void enclosePastures() {
		
		for(int y = rowRangeMin+1; y < rowRangeMax; y += 2 ) {
			for(int x = colRangeMin+1; x < colRangeMax; x+= 2) {
				((LotPasture)terrain.get(y, x)).enclosed = true;
			}
		}

		//north wall
		for(int x = colRangeMin+1; x < colRangeMax; x+= 2) {
			if(((LotFence)terrain.get(rowRangeMin, x)).built == false)
				((LotPasture)terrain.get(rowRangeMin+1, x)).enclosed = false;
		}
		//south wall
		for(int x = colRangeMin+1; x < colRangeMax; x+= 2) {
			if(((LotFence)terrain.get(rowRangeMax, x)).built == false)
				((LotPasture)terrain.get(rowRangeMax-1, x)).enclosed = false;
		}
		//west wall
		for(int y = rowRangeMin+1; y < rowRangeMax; y+= 2) {
			if(((LotFence)terrain.get(y, colRangeMin)).built == false)
				((LotPasture)terrain.get(y, colRangeMin+1)).enclosed = false;
		}
		//east wall
		for(int y = rowRangeMin+1; y < rowRangeMax; y+= 2) {
			if(((LotFence)terrain.get(y,colRangeMax)).built == false)
				((LotPasture)terrain.get(y, colRangeMax-1)).enclosed = false;
		}
		
		boolean didAnythingChange;
		int i = 0;
		do {
			if(i++ > 100) break;
			didAnythingChange = false;
			
			for(int y = rowRangeMin+1; y < rowRangeMax; y+= 2) {
				for(int x = colRangeMin+1; x < colRangeMax; x+= 2) {
					LotPasture lot = (LotPasture)terrain.get(y, x);
					if(lot.enclosed == false) {
						//try to go north
						if(y-2 > rowRangeMin && ((LotFence)terrain.get(y-1, x)).built == false) {
							didAnythingChange |= ((LotPasture)terrain.get(y-2, x)).enclosed;
							((LotPasture)terrain.get(y-2, x)).enclosed = false;
						}
						//try to go south
						if(y+2 < rowRangeMax && ((LotFence)terrain.get(y+1, x)).built == false) {
							didAnythingChange |= ((LotPasture)terrain.get(y+2, x)).enclosed;
							((LotPasture)terrain.get(y+2, x)).enclosed = false;
						}
						//try to go west
						if(x-2 > colRangeMin && ((LotFence)terrain.get(y, x-1)).built == false) {
							didAnythingChange |= ((LotPasture)terrain.get(y,  x-2)).enclosed;
							((LotPasture)terrain.get(y, x-2)).enclosed = false;
						}
						//try to go east
						if(x+2 < colRangeMax && ((LotFence)terrain.get(y, x+1)).built == false) {
							didAnythingChange |= ((LotPasture)terrain.get(y, x+2)).enclosed;
							((LotPasture)terrain.get(y, x+2)).enclosed = false;
						}
					}
				}
			}
		}
		while(didAnythingChange);
	}
	
}

