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
		assertEquals(new MazeNode(new Coordinate(1, 2)), 
				maze.getNode(new Coordinate(1, 2)));
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
	
	
	@Test
	public void testSetNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithinBounds() {
		fail("Not yet implemented");
	}
	
	private void buildMaze() {
		MazeBuilder builder = new MazeBuilder(3);
		maze = builder.toMaze();
	}

}
