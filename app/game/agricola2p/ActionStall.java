package game.agricola2p;

import game.GameError;
import game.agricola2p.ActionX.TaskExpansion;
import game.agricola2p.Board.State;

public class ActionStall extends Action {
	
	public ActionStall(Board board) {
		super(board);
	}

	protected void onNewRound() {
		super.onNewRound();
	}
	
	protected void onTake() throws GameError {
		super.onTake();
		board.inputState = State.BUILDING_STALLS;
		
		board.buildable.add(new Stall(board));
		board.stalls--;
	}

	protected void onTake(String[] params) throws GameError {
		
		switch(params[1]) {
		case "Build" :
			try {
				Integer x = Integer.parseInt(params[2]);
				Integer y = Integer.parseInt(params[3]);
				LotPasture lot = (LotPasture)board.activeFarm().terrain.get(y, x);
				lot.building = "stall";
			}
			catch(ArrayIndexOutOfBoundsException | NumberFormatException | ClassCastException e) {
				throw new GameError("Invalid Location ("+params[2]+","+params[3]+")");
			}
			board.buildable.remove(board.buildable.size()-1);
			break;
		default:
			throw new GameError("Command \""+params[1]+"\"");
		}
	}
	
	@Override
	public boolean getUsable() {
		Farm activeFarm = board.activeFarm();
		return super.getUsable() && board.stalls >= 1 && activeFarm.stone >= 3 && activeFarm.reed >= 1;
	}
}
