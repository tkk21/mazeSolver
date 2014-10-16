package maze;

public enum MazeDirection {
	up,down,left,right;
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
