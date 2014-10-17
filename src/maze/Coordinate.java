package maze;

/**
 * helper class to it abstracts away the x and y component of the coordinate
 * @author Ted Kim
 *
 */
public class Coordinate {

	/**a constant to store what the value of x component of the coordinate is*/
	private final int x;
	/**a constant to store what the value of y component of the coordinate is*/
	private final int y;

	/**
	 * constructor for coordinate sets the x and y value
	 * @param x	the x component of the coordinate
	 * @param y	the y component of the coordinate
	 */
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * gets the x component of the coordinate
	 * @return	the x component of the coordinate
	 */
	public int getX(){
		return x;
	}

	/**
	 * gets the y component of the coordinate
	 * @return	the y component of the coordinate
	 */
	public int getY(){
		return y;
	}

	/**
	 * checks if the coordinate is in the first quadrant
	 * @return	if the coordinate is in the first quandrant or not
	 */
	public boolean isFirstQuadrant(){
		return (x>-1 && y>-1);
	}

	/**
	 * a helper method to instantiate a coordinate 1 above this one
	 * @return	a coordinate 1 above this one
	 */
	public Coordinate getUpCoordinate(){
		return new Coordinate(x, y+1);
	}

	/**
	 * a helper method to instantiate a coordinate 1 below this one
	 * @return	a coordinate 1 below this one
	 */
	public Coordinate getDownCoordinate(){
		return new Coordinate(x, y-1);
	}

	/**
	 * a helper method to instantiate a coordinate 1 left of this one
	 * @return	a coordinate 1 left of this one
	 */
	public Coordinate getLeftCoordinate(){
		return new Coordinate(x-1, y);
	}

	/**
	 * a helper method to instantiate a coordinate 1 right of this one
	 * @return	a coordinate 1 right of this one
	 */
	public Coordinate getRightCoordinate(){
		return new Coordinate(x+1, y);
	}

	/**
	 * a helper method to instantiate a coordinate 1 to the direction specified
	 * @param direction	the direction to specify
	 * @return	a coordinate 1 to the direction specified
	 */
	public Coordinate getAdjacentCoordinate (MazeDirection direction){
		ExceptionUtils.checkNulls(direction);
		switch (direction){
		case up:
			return getUpCoordinate();
		case down:
			return getDownCoordinate();
		case left:
			return getLeftCoordinate();
		case right:
			return getRightCoordinate();
		default:
			return null;
		}
	}
	
	/**
	 * override of equals so that x and y are compared
	 */
	@Override
	public boolean equals(Object o){
		Coordinate c = castToCoordinate(o);
		if (c==null){
			return false;
		}
		if (getX() == c.getX() && getY() == c.getY()){
			return true;
		}
		return false;
	}

	/**
	 * helper method to cast Object into Coordinate
	 * @param o	Object to cast
	 * @return	the Object casted into Coordinate
	 */
	private Coordinate castToCoordinate(Object o){
		Coordinate coordinate = null;
		if (o==null){
			return null;
		}
		if (o instanceof Coordinate){
			coordinate = (Coordinate)o;
		}
		return coordinate;
	}
}
