package game.agricola2p;

import java.util.Set;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import game.GameError;
import game.agricola2p.Board.State;

public class ActionX extends Action {

	public class TaskExpansion extends Task {

		public TaskExpansion(Board board, String label, String command) {
			super(board, label, command);
		}

		public boolean getDisabled() {
			return (board.inputState != State.EXPANDING_FARM || board.expansions == 0);
		}

	}

	public int fences = 0;

	public ActionX(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
		this.fences += 1;
	}

	protected void onTake() throws GameError {
		super.onTake();
		board.activeFarm().fences += this.fences;
		this.fences = 0;
		board.inputState = State.EXPANDING_FARM;
		board.tasks.add(new TaskExpansion(board, "Expand Left", ":L"));
		board.tasks.add(new TaskExpansion(board, "Expand Right", ":R"));
	}

	protected void onTake(String[] params) {

		Farm farm = board.activeFarm();
		if (params.length < 2 || params[1] == null)
			return;
		else if (params[1].equals("R")) {
			farm.colRangeMax += 2;
		} else if (params[1].equals("L")) {
			farm.colRangeMin -= 2;
		} else
			throw new RuntimeException("Unknown Param " + params[1]);
		;

		ArrayTable<Integer, Integer, Lot> terrain = farm.createTerrainTable();
		terrain.putAll(farm.terrain);
		farm.terrain = terrain;

		for (Integer y : farm.getRowRange()) {
			for (Integer x : farm.getColRange()) {
				Lot tile = terrain.get(y, x);
				if (tile != null)
					continue;

				if (y % 2 == 0 && x % 2 == 0) {
					tile = new LotNull(x, y);
				} else if (y % 2 == 1 && x % 2 == 1) {
					tile = new LotPasture(x, y);
				} else {
					tile = new LotFence(x, y);
				}
				terrain.put(y, x, tile);
			}
		}

		board.inputState = State.WAITING_ON_COMMIT;
	}

}
