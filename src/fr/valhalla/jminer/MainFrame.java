package fr.valhalla.jminer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainFrame extends Frame {

	private Label label = new Label();
	
	private Plateau plateau;
	private Panel upPanel;
	private Panel downPanel;
	
	/**
	 * Constructor : initialize the main frame and the game.
	 * @throws HeadlessException
	 */
	public MainFrame() throws HeadlessException {
		// Setup GUI
		upPanel = new Panel();
		downPanel = new Panel();
		this.setLayout(new BorderLayout());
		this.add(upPanel, BorderLayout.CENTER);
		this.add(downPanel, BorderLayout.SOUTH);
		
		// Setup status bar
		downPanel.setLayout(new BorderLayout());
		downPanel.add(label, BorderLayout.CENTER);
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout(new FlowLayout());
		Button quitButton = new Button("Quit");
		Button resetButton = new Button("Reset");
		Button revealButton = new Button("Reveal");
		downPanel.add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.add(quitButton);
		buttonsPanel.add(resetButton);
		buttonsPanel.add(revealButton);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		revealButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plateau.revealAll();
				upPanel.repaint();
			}
		});

		// Setup plateau
		setup();
		// prepare to draw -- use setVisible(true) to display
		this.pack();
	}
	
	private void setup() {
		// Setup plateau layout
		PlateauLayout plateauLayout = new PlateauLayout(Configuration.rows,Configuration.cols);
		upPanel.setLayout(plateauLayout);
		// Setup plateau
		int chances = Configuration.alea; // % chances to have a mine 
		plateau = new Plateau(this);
		// Fill plateau with tiles
		for(int i = 0 ; i < plateauLayout.getSize() ; i++) {
			// Fill the tile: mine or not ?
			boolean mined;
			Random rand = new Random();
			if(rand.nextInt(100) < chances)
				mined = true;
			else
				mined = false;
			// Tile position
			int x = i / plateauLayout.getColumns();
			int y = i % plateauLayout.getColumns();
			// Make the tile 
			Tile t = new Tile(plateau, x, y, mined);
			upPanel.add(t);
			plateau.add(t);
		}
		// configure tiles
		plateau.initPlateau();
	}
	
	/**
	 * Set the given text to the status label.
	 * @param text
	 */
	public void setLabel(String text) {
		this.label.setText(text);
	}
	
	/**
	 * Update the status label with the current score
	 * @param tiles : total number of tiles on the plateau
	 * @param locked : tiles locked (secured mines)
	 * @param mined : number of mines (locked or exploded)
	 * @param revealed : number of revealed tiles
	 * @param exploded : number of mines exploded
	 */
	public void updateLabel(int tiles, int locked, int mined, int revealed, int exploded) {
		int score = tiles * (locked - exploded*2) + revealed;
		String s = revealed + "/" + tiles + " tiles revealed, " + locked + "/" + mined + " mines secured. " + exploded + " mines exploded. SCORE: " + score ;
		setLabel(s);
	}

}
