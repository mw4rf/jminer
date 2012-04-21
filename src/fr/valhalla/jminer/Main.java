package fr.valhalla.jminer;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;


public class Main {
	
	/**
	 * Launch the game
	 * @param args
	 */
	public static void main(String args[]) {
		
		// Get arguments from command line (or use defaults)
		Configuration.initConfiguration(args);
		
		// Build main frame
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
		
		// Exit program on main frame close
		mf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
	}
}
