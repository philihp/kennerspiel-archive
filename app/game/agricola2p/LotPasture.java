package game.agricola2p;

import com.google.common.collect.ArrayTable;

/**
 * Things that might exist on the landscape should extend this.
 */
public class LotPasture extends Lot{
	
	public int sheep;
	public int boars;
	public int cattle;
	public int horses;
	public boolean trough;
	public String building;
	public boolean enclosed;
	
	public LotPasture(int x, int y) {
		super(x,y);
		this.trough = false;
		this.sheep = 0;
		this.boars = 0;
		this.cattle = 0;
		this.horses = 0;
	}
	
	public String getType() {
		return "farmtile";
	}
}
