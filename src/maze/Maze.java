package maze;


/**
 * helper class to represent the 2 dimensional maze array
 * utilizes the coordinate helper class
 * so that the coordinate itself is the key to a node  
 * @author Ted Kim
 *
 */
public class Maze {

	/**the array to represent the maze*/
	private MazeNode[][] mazeArr;
	
	/**
	 * initializes the maze
	 * maze is initially full of null nodes
	 * @param size	the size of the maze
	 */
	public Maze (int size){
		mazeArr = new MazeNode[size][size];
		//actual nodes are built from the maze builder
	}
	
	/**
	 * gets the node at a specified coordinate
	 * @param coordinate	the coordinate of the node
	 * @return	the node at the coordinate
	 */
	public MazeNode getNode (Coordinate coordinate){
		return mazeArr[coordinate.getX()][coordinate.getY()];
	}
	
	/**
	 * sets the node at a specified coordinate
	 * @param coordinate	the coordinate of the node
	 * @param node
	 */
	public void setNode (Coordinate coordinate, MazeNode node){
		mazeArr[coordinate.getX()][coordinate.getY()] = node;
	}
	
	/**
	 * gets the size of the maze
	 * @return	the size of the maze
	 */
	public int size(){
		return mazeArr.length;
	}
	
	/**
	 * checks if the coordinate is within the bounds of the maze
	 * @param coordinate	the coordinate to check
	 * @return	if the coordinate is within the bounds of the maze or not
	 */
	public boolean isWithinBounds (Coordinate coordinate){
		return coordinate.isFirstQuadrant() && 
				coordinate.getX()<size() && 
				coordinate.getY()<size();
	}
}
