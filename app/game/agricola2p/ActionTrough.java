package game.agricola2p;

import game.GameError;
import game.agricola2p.ActionX.TaskExpansion;
import game.agricola2p.Board.State;

public class ActionTrough extends Action {

	public class TaskBuyStoneFence extends Task {

		public TaskBuyStoneFence(Board board, String label, String command) {
			super(board, label, command);
		}

		public boolean getDisabled() {
			return board.troughs == 0 || board.activeFarm().wood < 3;
		}

	}
	
	public ActionTrough(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.inputState = State.BUILDING_TROUGHS;
		board.tasks.add(new TaskBuyStoneFence(board, "Buy Additional Trough", ":Buy"));
		
		board.buildable.add(new Trough(board));
		board.troughs--;
	}

	protected void onTake(String[] params) throws GameError {
		
		switch(params[1]) {
		case "Trough" :
			try {
				Integer x = Integer.parseInt(params[2]);
				Integer y = Integer.parseInt(params[3]);
				LotPasture lot = (LotPasture)board.activeFarm().terrain.get(y, x);
				lot.trough = true;
			}
			catch(ArrayIndexOutOfBoundsException | NumberFormatException | ClassCastException e) {
				throw new GameError("Invalid Location ("+params[2]+","+params[3]+")");
			}
			board.buildable.remove(board.buildable.size()-1);
			break;
		case "Buy" :
			if(board.activeFarm().wood < 3) {
				throw new GameError("Not enough wood to build another trough");
			} 
			else {
				board.activeFarm().wood -= 3;
				board.troughs--;
				board.buildable.add(new Trough(board));
			}
			break;
		default:
			throw new GameError("Command \""+params[1]+"\"");
		}
	}
	
	@Override
	public boolean getUsable() {
		return super.getUsable() && board.troughs >= 1;
	}
}
