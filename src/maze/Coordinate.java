package maze;

/**
 * helper class to it abstracts away the x and y component of the coordinate
 * @author ted
 *
 */
public class Coordinate {

	private final int x;
	private final int y;

	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public boolean isFirstQuadrant(){
		return (x>-1 && y>-1);
	}

	public Coordinate getUpCoordinate(){
		return new Coordinate(x, y+1);
	}

	public Coordinate getDownCoordinate(){
		return new Coordinate(x, y-1);
	}

	public Coordinate getLeftCoordinate(){
		return new Coordinate(x-1, y);
	}

	public Coordinate getRightCoordinate(){
		return new Coordinate(x+1, y);
	}

	public Coordinate getAdjacentCoordinate (MazeDirection direction){
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
	
	@Override
	public boolean equals(Object o){
		Coordinate c = castToCoordinate(o);
		if (getX() == c.getX() && getY() == c.getY()){
			return true;
		}
		return false;
	}

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
