package maze;

/**
 * A class to define the nodes of the maze
 * @author Ted Kim
 *
 */
public class MazeNode {

	//false if no plank exists, true if there is a plank
	
	/**boolean field to represent if there's a plank above this node*/
	//boolean up
	private boolean up;
	/**boolean field to represent if there's a plank below this node*/
	//boolean down
	private boolean down;
	/**boolean field to represent if there's a plank left of this node*/
	//boolean left
	private boolean left;
	/**boolean field to represent if there's a plank right of this node*/
	//boolean right
	private boolean right;
	/**boolean field to represent if this plank is visited*/
	//boolean visited
	private boolean visited;
	/**
	 * realized that the coordinate is necessary to differentiate between
	 * nodes with same ladder configurations
	 * 
	 * Error Fixing
	 * as faithful as possible to the pseudocode
	 * */
	private Coordinate coordinate;
	
	/**
	 * constructs the maze node
	 * @param coordinate	the coordinate to make this node at
	 */
	public MazeNode (Coordinate coordinate){
		this.coordinate = coordinate;
	}
	
	/**
	 * @return the up
	 */
	public boolean hasUp() {
		return up;
	}
	
	/**
	 * @return the down
	 */
	public boolean hasDown() {
		return down;
	}
	
	/**
	 * @return the left
	 */
	public boolean hasLeft() {
		return left;
	}
	
	/**
	 * @return the right
	 */
	public boolean hasRight() {
		return right;
	}
	
	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}
	
	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	/**
	 * puts down the ladder in the direction specified
	 * @param direction	the direction to specify
	 */
	public void putDownLadder (MazeDirection direction){
		switch(direction){
		case up:
			setUp(true);
			break;
		case down:
			setDown(true);
			break;
		case left:
			setLeft(true);
			break;
		case right:
			setRight(true);
			break;
		default:
			System.out.println("should not be able to reach this switch case");
			break;
		}
	}
	
	/**
	 * checks if there's a ladder in the direction specified
	 * @param direction
	 * @return
	 */
	public boolean hasLadder (MazeDirection direction){
		switch(direction){
		case up:
			return hasUp();
		case down:
			return hasDown();
		case left:
			return hasLeft();
		case right:
			return hasRight();
		default:
			System.out.println("a call to hasLadder with illegal direction has been made");
			return false;
		}
	}
	
	/**
	 * returns a node with same planks and coordinate, but with cleared visited
	 * @param node	the node to copy
	 * @return	the copied node
	 */
	public static MazeNode copyNode (MazeNode node){
		MazeNode newNode = new MazeNode(node.getCoordinate());
		newNode.setUp(node.hasUp());
		newNode.setDown(node.hasDown());
		newNode.setLeft(node.hasLeft());
		newNode.setRight(node.hasRight());
		newNode.visited = false;
		return newNode;
	}

	/**
	 * @param up the up to set
	 */
	private void setUp(boolean up) {
		this.up = up;
	}

	/**
	 * @param down the down to set
	 */
	private void setDown(boolean down) {
		this.down = down;
	}

	/**
	 * @param left the left to set
	 */
	private void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * @param right the right to set
	 */
	private void setRight(boolean right) {
		this.right = right;
	}
}
