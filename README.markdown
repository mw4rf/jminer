# Java Minebreaker

## Usage

Either double-click on the JAR file to launch the game, or use the command line :
<pre>java -jar jminer.jar</pre>

The command line accept 3 arguments :
	*	**-c** or **--cols** : the number of **columns** on the grid
	*	**-r** or **--rows** : the number of **rows** on the grid
	*	**-a** or **--alea** : the percentage of chances to have a mine on a tile.
	
e.g. <pre>java -jar jminer.jar -c 30 -r 20 -a 10</pre> creates a 30x20 grid, and each tile has 10% chances to have a mine. 

