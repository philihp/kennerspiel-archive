package game.agricola2p;

public class TileFence extends Tile {
	
	public boolean built;
	
	public enum DIRECTION { hfence, vfence };
	
	public DIRECTION type;
	
	public TileFence(int x, int y) {
		super(x,y);
		built = false;
		if(x % 2 == 0) {
			this.type = DIRECTION.vfence;
		}
		else {
			this.type = DIRECTION.hfence;
		}
		
	}
	
}
