package fr.valhalla.jminer;
import java.awt.GridLayout;
import java.util.ArrayList;


public class PlateauLayout extends GridLayout {
	
	/**
	 * Constructor
	 * @param rows
	 * @param cols
	 */
	public PlateauLayout(int rows, int cols) {
		super(rows, cols);
	}
	
	/**
	 * Get the size of the plateau (columns * rows), which is equal to the number of tiles.
	 * @return
	 */
	public int getSize() {
		return super.getColumns() * super.getRows();
	}
	
	

}
