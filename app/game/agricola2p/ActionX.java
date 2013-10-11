package game.agricola2p;

import java.util.Set;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import game.agricola2p.Board.State;

public class ActionX extends Action {

	public int fences = 0;
	
	public ActionX(Board board) {
		super(board);
	}
	
	protected void onNewRound() {
		super.onNewRound();
		this.fences += 1;
	}
	
	protected void onTake() {
		super.onTake();
		board.activeFarm().fences += this.fences;
		this.fences = 0;
		board.inputState = State.EXPANDING_FARM;
	}
	
	protected void onTake(String[] params) {
		
		Farm farm = board.activeFarm();
		if(params.length < 2 || params[1] == null) return;
		else if(params[1].equals("R")) {
			farm.colRangeMax += 2;
		}
		else if(params[1].equals("L")) {
			farm.colRangeMin -= 2;
		}
		else throw new RuntimeException("Unknown Param "+params[1]);;
		
		ArrayTable<Integer, Integer, Tile> terrain = farm.createTerrainTable();
		terrain.putAll(farm.terrain);
		farm.terrain = terrain;
		
		for(Integer y : farm.getRowRange()) {
			for(Integer x : farm.getColRange()) {
				Tile tile = terrain.get(y, x);
				if(tile != null) continue;
				
				if(y % 2 == 0 && x % 2 == 0) {
					tile = new TileNull(x,y);
				}
				else if(y % 2 == 1 && x % 2 == 1) {
					tile = new TilePasture(x,y);
				}
				else {
					tile = new TileFence(x,y);
				}
				terrain.put(y, x, tile);
			}
		}
		
		board.inputState = State.WAITING_ON_COMMIT;
	}
	
}