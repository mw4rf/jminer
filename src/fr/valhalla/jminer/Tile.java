package fr.valhalla.jminer;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Tile extends Panel implements MouseListener {
	
	public Plateau plateau;
	
	public Label label = new Label();
	public boolean mined = false;
	public boolean revealed = false;
	public int minesAround = 0;
	public boolean locked = false;
	
	public int x = 0;
	public int y = 0;
	
	/**
	 * Constructor
	 * @param plateau
	 * @param x
	 * @param y
	 * @param mined
	 */
	public Tile(Plateau plateau, int x, int y, boolean mined) {
		this.plateau = plateau;
		this.x = x;
		this.y = y;
		this.mined = mined;
		
		label.setText(" ");
		
		// add label container
		this.add(label);
		
		// add listeners
		addMouseListener(this);
		label.addMouseListener(this);
		
		// set GUI
		this.setBackground(Color.BLUE);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.revealTile();
		this.plateau.updateStatus();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Returns TRUE if the the tiles share a side or a corner (they are adjacent).
	 * i.e. tile (4,4) have those adjacent tiles: 	3,3 ; 3,4 ; 3,5 ; 4,3 ; 4,5 ; 5,3 ; 5,4 ; 5,5
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static boolean nextTo(Tile t1, Tile t2) {
		if(t1.x == t2.x || t1.x == t2.x - 1 || t1.x == t2.x + 1)
			if(t1.y == t2.y || t1.y == t2.y - 1 || t1.y == t2.y + 1)
				return true;
		return false;
	}
	
	/**
	 * Increment the number of mines around this tile.
	 */
	public void addMineAround() {
		this.minesAround++;
		this.repaint();
	}
	
	/**
	 * Reveal the given tile. 
	 * <ul>
	 * <li />If the tile is mined, the mine is revealed. COLOR: RED.
	 * <li />If the tile is NOT mined and has no mine around, it is revealed, and all tiles with no mine
	 * around are revealed in cascade. COLOR: GREEN.
	 * <li />If the tile is NOT mined and has at least 1 mine around, it is revealed and the number of
	 * mines around is printed. COLOR: ORANGE. Then, each mine around is checked and locked if all surrounding tiles
	 * have been revealed. COLOR: YELLOW (for locked mines).
	 */
	public void revealTile() {
		// Tile revealed
		this.revealed = true;
		
		// Mined tile
		if(mined) {
			label.setText("M");
			this.setBackground(Color.RED);
			Sound.mineExploded();
		}
		// No mine here
		else {
			// At least 1 mine around
			if(minesAround > 0) {
				label.setText(minesAround+"");
				this.setBackground(Color.ORANGE);
				// If the mine is surrounded by revealed tiles, lock it
				plateau.checkMines(this);
			} 
			// No mine around
			else {
				label.setText(" ");
				this.setBackground(Color.GREEN);
				// Cascade reveal (adjacent tiles)
				plateau.revealAround(this);
			}
		}
		this.repaint();
	}
	
	/**
	 * Lock the tile. Locked tiles are mined tiles with all the tiles around it revealed.
	 * When all mined tiles are locked, the player wins.
	 */
	public void lockTile() {
		this.locked = true;
		label.setText("*");
		this.setBackground(Color.YELLOW);
		this.repaint();
	}
	
}
