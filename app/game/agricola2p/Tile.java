package game.agricola2p;

/**
 * Things that might exist on the landscape should extend this.
 */
abstract class Tile {
	public int x;
	public int y;
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
