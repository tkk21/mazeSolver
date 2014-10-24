package test;

import static org.junit.Assert.*;
import maze.Coordinate;
import maze.Maze;
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

	@Test
	public void testGetNode() {
		fail("Not yet implemented");
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

}
