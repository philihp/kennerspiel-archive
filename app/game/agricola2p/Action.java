package game.agricola2p;

import game.agricola2p.Board.State;

import com.fasterxml.jackson.annotation.JsonIgnore;

abstract class Action {
	
	@JsonIgnore
	protected Board board;
	
	public Worker occupant = null;
	
	public Action(Board board) {
		this.board = board;
	}
	
	protected void onNewRound() {
		if(occupant != null) {
			if(occupant.getColor().equals("red")) {
				board.redFarm.workers.add(occupant);
			}
			else {
				board.blueFarm.workers.add(occupant);
			}

			this.occupant = null;	
		}
	}
	
	protected void onTake() {
		if(board.activeFarm().workers.size() == 0) return;
		
		Worker worker = board.activeFarm().workers.remove(0);
		worker.setUsable(false);
		this.occupant = worker;

		board.canCommit = true;
	}
	
	protected void onTake(String[] params) {
		//typically follow-up moves, such as "build fence" "which fence" "which fence" "which fence"
	}
	
}
