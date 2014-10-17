package maze;

/**
 * An enum to define the directions a ladder can be placed
 * @author Ted Kim
 *
 */
public enum MazeDirection {
	/**the four directions*/
	up,down,left,right;
	
	/**
	 * tells the opposite direction of this direction
	 * @return	the opposite direction of this direction
	 */
	public MazeDirection opposite(){
		switch(this){
		case up:
			return down;
		case down:
			return up;
		case left:
			return right;
		case right:
			return left;
		default:
			System.out.println("This default case shouldn't happen");
			return null;
		}
	}
}
