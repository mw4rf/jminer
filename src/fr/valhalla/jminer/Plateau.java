package fr.valhalla.jminer;
import java.util.ArrayList;
import fr.valhalla.jminer.Tile;


public class Plateau extends ArrayList<Tile> {
	
	public MainFrame parent;
	
	/**
	 * Constructor
	 * @param parent
	 */
	public Plateau(MainFrame parent) {
		this.parent = parent;
	}
	
	/**
	 * Setup the plateau, telling each tile how many mines are around it.
	 */
	public void initPlateau() {
		for(Tile t : this) {
			for(Tile t1 : this)
				if(Tile.nextTo(t, t1))
					if(t.mined && !t1.mined)
						t1.addMineAround();
		}
	}
	
	/**
	 * Reveal all the tiles around the given tile if the following conditions are met:
	 * the tile must not be mined, already revealed, or have any mine around it.
	 * @param t
	 */
	public void revealAround(Tile t) {
		for(Tile t1 : this)
			if(Tile.nextTo(t1, t) && !t1.mined && !t1.revealed && t1.minesAround == 0) {
				t1.revealTile();
				this.revealAround(t1);
			}
	}
	
	/**
	 * Check all the mines around the given tile, and lock them if
	 * all the other tiles around them are revealed.
	 * @param t
	 */
	public void checkMines(Tile t) {
		// Find the mines
		for(Tile t1 : this)
			if(Tile.nextTo(t1, t) && t1.mined && !t1.revealed) {
				// Check mine surroundings
				boolean revealed = true;
				for(Tile t2 : this)
					if(Tile.nextTo(t2, t1) && !t2.revealed && !t2.mined)
						revealed = false;
				if(revealed)
					t1.lockTile();
			}
	}
	
	/**
	 * Update status bar (score).
	 */
	public void updateStatus() {
		int tiles = this.size();
		int revealed = 0;
		int mined = 0;
		int locked = 0;
		int exploded = 0;
		for(Tile t : this)
			if(t.locked)
				locked++;
			else if(t.mined && t.revealed)
				exploded++;
			else if(t.mined)
				mined++;
			else if(t.revealed)
				revealed++;
		parent.updateLabel(tiles, locked, mined, revealed, exploded);
	}

	/**
	 * Reveal all tiles (game over...)
	 */
	public void revealAll() {
		for(Tile t : this)
			t.revealTile();
	}
	
}
