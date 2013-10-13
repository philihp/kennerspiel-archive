package game.agricola2p;

import game.GameError;
import game.agricola2p.ActionSFen.TaskBuyStoneFence;
import game.agricola2p.Board.State;

public class ActionWFen extends Action {

	public class TaskBuyWoodFence extends Task {

		public TaskBuyWoodFence(Board board, String label, String command) {
			super(board, label, command);
		}

		public boolean getDisabled() {
			return (board.activeFarm().wood < 1) || (board.activeFarm().fences < 1);
		}

	}
	public ActionWFen(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.inputState = State.BUILDING_FENCES;
		board.tasks.add(new TaskBuyWoodFence(board, "Buy Additional Fence", ":Buy"));
		onTake(new String[] {"","Buy"});
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
			if(board.activeFarm().wood < 1) {
				throw new GameError("Not enough wood to build a fence");
			} 
			else {
				board.activeFarm().wood -= 1;
				board.activeFarm().fences--;
				board.buildable.add(new Fence(board));
			}
			break;
		default:
			throw new GameError("Command \""+params[1]+"\"");
		}
	}

	@Override
	public boolean getUsable() {
		return super.getUsable() && board.activeFarm().wood >= 1 && board.activeFarm().fences >= 1;
	}
}
