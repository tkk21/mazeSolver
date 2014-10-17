package maze;

public class MazeBuilder {

	private Maze maze;

	public MazeBuilder (int size){
		maze = new Maze(size);
		buildNodes();
	}
	
	/**
	 * sets the ladder boolean as true in both directions
	 * @param direction
	 * @param source
	 */
	public void putDownLadder(MazeDirection direction, Coordinate source){
		tryToPutDownLadder(source, source.getAdjacentCoordinate(direction), direction);
	}
	
	public void copyExistingMaze(Maze copy){
		for (int x = 0;x<copy.size();x++){
			for (int y = 0;y<copy.size();y++){
				Coordinate coordinate = new Coordinate(x, y);
				maze.setNode(coordinate, MazeNode.copyNode(copy.getNode(coordinate)));
			}
		}
	}
	public Maze toMaze(){
		return maze;
	}
	
	private void buildNodes(){
		for (int x = 0; x<maze.size(); x++){
			for (int y = 0; y<maze.size(); y++){
				Coordinate coordinate = new Coordinate(x, y);
				maze.setNode(coordinate, new MazeNode(coordinate));
			}
		}
	}
	
	private boolean tryToPutDownLadder(Coordinate from, Coordinate to, MazeDirection direction){
		if (!maze.isWithinBounds(to)){
			return false;
		}
		maze.getNode(from).putDownLadder(direction);
		maze.getNode(to).putDownLadder(direction.opposite());
		return true;
	}
}
