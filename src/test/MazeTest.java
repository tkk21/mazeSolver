package test;

import static org.junit.Assert.*;
import maze.Coordinate;
import maze.Maze;
import maze.MazeBuilder;
import maze.MazeNode;

import org.junit.Before;
import org.junit.Test;

public class MazeTest {

	Maze maze;
	
	@Before
	public void reset(){
		maze = null;
	}
	
	/**
	 * Structured basis
	 * Good data
	 */
	@Test
	public void testMaze_positiveSize() {
		maze = new Maze(5);
		assertEquals(5, maze.size());
	}
	
	/**
	 * Bad data
	 * 
	 * The constructor should have been written more defensively.
	 * It should have checked to make sure the size was not negative.
	 * If it was, a default size maze should have been constructed
	 * instead of exiting from the constructor which results in unreachable objects
	 */
	@Test(expected=NegativeArraySizeException.class)
	public void testMaze_negativeSize() {
		maze = new Maze(-3);
		assertEquals(-3, maze.size());
	}
	
	/**
	 * Bad data
	 * 
	 * nothing bad actually happens until a node is about to be set
	 */
	@Test
	public void testMaze_zeroSize() {
		maze = new Maze(0);
		assertEquals(0, maze.size());
	}

	/**
	 * Structured Basis
	 */
	@Test
	public void testGetNode_withinMaze() {
		buildMaze();
		assertTrue(nodeEquals(new MazeNode(new Coordinate(1, 2)), 
				maze.getNode(new Coordinate(1, 2))));
	}
	
	/**
	 * Bad data
	 * 
	 * all the methods that calls getNode already checks to make sure 
	 * that the coordinate is in the maze
	 * or only loops through the inside of the maze.
	 * So currently, this wouldn't cause much problem.
	 * 
	 * However, in the future, there might be methods that call getNode without checking
	 * that the coordinate is in the maze.
	 * Thus, there should be a check for this even though it is redundant at the moment
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetNode_outOfMaze() {
		buildMaze();
		assertEquals(null, maze.getNode(new Coordinate(-3, -2)));
	}

	/**
	 * Bad data
	 * 
	 * This is something that should happen.
	 * If a null coordinate is passed in,
	 * there should be an exception to warn the user
	 */
	@Test(expected=NullPointerException.class)
	public void testGetNode_nullCoordinate() {
		buildMaze();
		assertEquals(null, maze.getNode(null));
	}
	
	/**
	 * Structural basis
	 * Good data
	 * 
	 * realized that the coordinate could be extracted from the MazeNode
	 * reducing the number of parameters for setNode may reduce room for errors
	 */
	@Test
	public void testSetNode_withinMaze() {
		maze = new Maze(3);
		maze.setNode(new Coordinate(1, 2), new MazeNode(new Coordinate(1, 2)));
		assertTrue(nodeEquals(new MazeNode(new Coordinate(1, 2)), 
				maze.getNode(new Coordinate(1, 2))));
	}

	/**
	 * Bad data
	 * 
	 * Similarly to getNode,
	 * currently, there should be no way this case would be reached
	 * as the loop in methods that calls setNode
	 * only loops inside the maze
	 * 
	 * However, for the same reason,
	 * there should be a redundant check on this method
	 * as future methods may not check to make sure coordinate is in the maze
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testSetNode_outOfMaze() {
		maze = new Maze(3);
		maze.setNode(new Coordinate(1, 5), new MazeNode(new Coordinate(1, 5)));
	}
	
	/**
	 * Bad data
	 */
	@Test(expected=NullPointerException.class)
	public void testSetNode_nullCoordinate() {
		maze = new Maze(3);
		maze.setNode(null, new MazeNode(null));
	}
	
	/**
	 * Structural basis
	 * 
	 * there is no need for other cases as those are covered by the constructor
	 */
	@Test
	public void testSize() {
		maze = new Maze(4);
		assertEquals(4, maze.size());
	}

	/**
	 * cases for withinBounds
	 * 1. nominal case, first quadrant and x<size and y<size
	 * 2. x>size
	 * 3. y>size
	 * 4. not first quadrant
	 * 5. null coordinate
	 */
	
	/**
	 * Structural basis
	 * Good data
	 * Boundary 
	 */
	@Test
	public void testWithinBounds_nominal() {
		buildMaze();
		for (int i = 0; i<maze.size();i++){
			for(int j = 0; j<maze.size();j++){
				assertTrue("failed at " + i+ "," + j,
						maze.withinBounds(new Coordinate(i, j)));
			}
		}
		//there's no node that we missed
		assertFalse(maze.withinBounds(new Coordinate(3, 3)));
	}
	
	private void buildMaze() {
		MazeBuilder builder = new MazeBuilder(3);
		maze = builder.toMaze();
	}
	
	/**
	 * realized that there was no equals method for nodes
	 * @param a
	 * @param b
	 * @return
	 */
	static boolean nodeEquals(MazeNode a, MazeNode b){
		boolean equal = true;
		equal &= (a.hasUp()==b.hasUp());
		equal &= (a.hasDown()==b.hasDown());
		equal &= (a.hasLeft()==b.hasLeft());
		equal &= (a.hasRight()==b.hasRight());
		equal &= (a.isVisited()==b.isVisited());
		equal &= (a.getCoordinate().equals(b.getCoordinate()));
		return equal;
	}

}
