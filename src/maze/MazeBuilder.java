package maze;

/**
 * a helper class to build the maze by
 * setting nodes to the maze
 * and setting ladders
 * @author Ted Kim
 *
 */
public class MazeBuilder {

	/**the maze that is being built*/
	private Maze maze;

	/**
	 * constructs a maze and fills it with nodes
	 * @param size	the size of the maze
	 */
	public MazeBuilder (int size){
		maze = new Maze(size);
		buildNodes();
	}
	
	/**
	 * sets the ladder boolean as true in both directions
	 * this is done to avoid problems like putting ladder  one way, but forgetting to the other way
	 * @param direction	the direction to put down the ladder
	 * @param source	the node the ladder is being put down from
	 */
	public void placeLadder(MazeDirection direction, Coordinate source){
		Coordinate destination = source.getAdjacentCoordinate(direction);
		if (!maze.withinBounds(destination)){
			return;
		}
		maze.getNode(source).putDownLadder(direction);
		maze.getNode(destination).putDownLadder(direction.opposite());
	}
	
	/**
	 * copies the nodes from an existing maze to this builder
	 * @param copy	the maze to copy
	 */
	public void copyExistingMaze(Maze copy){
		for (int x = 0;x<copy.size();x++){
			for (int y = 0;y<copy.size();y++){
				Coordinate coordinate = new Coordinate(x, y);
				maze.setNode(coordinate, MazeNode.copyNode(copy.getNode(coordinate)));
			}
		}
	}
	
	/**
	 * returns the maze this builder is building
	 * @return	the maze this builder is building
	 */
	public Maze toMaze(){
		return maze;
	}
	
	/**
	 * sets a node to all coordinates in the maze
	 */
	private void buildNodes(){
		for (int x = 0; x<maze.size(); x++){
			for (int y = 0; y<maze.size(); y++){
				Coordinate coordinate = new Coordinate(x, y);
				maze.setNode(coordinate, new MazeNode(coordinate));
			}
		}
	}
}
