package maze;


/**
 * helper class to represent the 2 dimensional maze array
 * utilizes the coordinate helper class
 * so that the coordinate itself is the key to a node  
 * @author Ted Kim
 *
 */
public class Maze {

	private MazeNode[][] mazeArr;
	
	public Maze (int size){
		mazeArr = new MazeNode[size][size];
		//actual nodes are built from the maze builder
	}
	
	MazeNode getNode (Coordinate coordinate){
		return mazeArr[coordinate.getX()][coordinate.getY()];
	}
	
	void setNode (Coordinate coordinate, MazeNode node){
		mazeArr[coordinate.getX()][coordinate.getY()] = node;
	}
	
	public int size(){
		return mazeArr.length;
	}
	
	public boolean isWithinBounds (Coordinate coordinate){
		return coordinate.isFirstQuadrant() && 
				coordinate.getX()<size() && 
				coordinate.getY()<size();
	}
}
