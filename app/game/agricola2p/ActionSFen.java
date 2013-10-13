package game.agricola2p;

import game.GameError;
import game.agricola2p.ActionX.TaskExpansion;
import game.agricola2p.Board.State;

public class ActionSFen extends Action {

	public class TaskBuyStoneFence extends Task {

		public TaskBuyStoneFence(Board board, String label, String command) {
			super(board, label, command);
		}

		public boolean getDisabled() {
			return (board.activeFarm().stone < 2) || (board.activeFarm().fences < 1);
		}

	}
	
	public ActionSFen(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.inputState = State.BUILDING_FENCES;
		board.tasks.add(new TaskBuyStoneFence(board, "Buy Additional Fence", ":Buy"));
		
		board.buildable.add(new Fence(board, "fence"));
		board.activeFarm().fences--;
		
		if(board.activeFarm().fences >= 1) {
			board.buildable.add(new Fence(board, "fence"));
			board.activeFarm().fences--;
		}
	}

	protected void onTake(String[] params) throws GameError {
		switch(params[1]) {
		case "Fence" :
			if(board.buildable.isEmpty()) {
				throw new GameError("No fences available to build");
			}
			try {
				Integer x = Integer.parseInt(params[2]);
				Integer y = Integer.parseInt(params[3]);
				((LotFence)board.activeFarm().terrain.get(y, x)).built = true;
			}
			catch(ArrayIndexOutOfBoundsException | NumberFormatException | ClassCastException e) {
				throw new GameError("Invalid Location ("+params[2]+","+params[3]+")");
			}
			board.buildable.remove(board.buildable.size()-1);
			break;
		case "Buy" :
			if(board.activeFarm().stone < 2) {
				throw new GameError("Not enough stone to build a fence");
			} 
			else {
				board.activeFarm().stone -= 2;
				board.activeFarm().fences--;
				board.buildable.add(new Fence(board, "fence"));
			}
			break;
		default:
			throw new GameError("Command \""+params[1]+"\"");
		}
	}
	
	@Override
	public boolean getUsable() {
		return super.getUsable() && board.activeFarm().stone >= 2 && board.activeFarm().fences >= 1;
	}
}
