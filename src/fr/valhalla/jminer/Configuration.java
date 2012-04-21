package fr.valhalla.jminer;

public class Configuration {
	
	/**
	 * Number of COLUMNS on the grid.
	 */
	public static Integer cols = 40;
	/**
	 * Number of ROWS on the grid.
	 */
	public static Integer rows = 20;
	/**
	 * Percentage of CHANGES to have a mine on a tile.
	 */
	public static Integer alea = 10;
	
	/**
	 * Init configuration (from command line arguments)
	 * @param args
	 */
	public static void initConfiguration(String args[]) {
		int i = 0;
		String arg;
		while (i < args.length && args[i].startsWith("-")) {
			arg = args[i++];
			// Columns
			if (arg.equals("-c") || arg.equals("--cols")) {
				if (i < args.length)
					Configuration.cols = new Integer(args[i++]);
				else
					System.err.println("-c/--cols requires an integer");
			}
			// Rows
			if (arg.equals("-r") || arg.equals("--rows")) {
				if (i < args.length)
					Configuration.rows = new Integer(args[i++]);
				else
					System.err.println("-r/--rows requires an integer");
			}
			// Alea
			if (arg.equals("-a") || arg.equals("--alea")) {
				if (i < args.length)
					Configuration.alea = new Integer(args[i++]);
				else
					System.err.println("-a/--alea requires an integer");
			}
		}
		System.out.println("Configuration: " + cols + " columns, " + rows + " rows, " + alea + "% of chances to get a mine on a tile.");
	}

}
