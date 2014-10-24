package test;

import static org.junit.Assert.*;
import maze.Coordinate;
import maze.MazeDirection;
import maze.MazeNode;

import org.junit.Before;
import org.junit.Test;

public class MazeNodeTest {

	MazeNode node;
	
	@Before
	public void reset() {
		node = new MazeNode(new Coordinate(2, 2));
	}
	
	/**
	 * constructor simply sets coordinate
	 * defined and exited
	 * this is okay since coordinate is a field
	 * 
	 * constructor doesn't really have a boundary to deal with
	 * other classes deal with that
	 */
	/**
	 * structural basis
	 */
	@Test
	public void testMazeNode_nominal() {
		assertEquals(new Coordinate(2, 2), node.getCoordinate());
	}
	
	/**
	 * bad data
	 */
	@Test
	public void testMazeNode_nullCoordinate() {
		
	}

	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasUp() {
		node.placeLadder(MazeDirection.up);
		assertTrue(node.hasUp());
	}
	
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasUp_false() {
		assertFalse(node.hasUp());
	}

	@Test
	public void testHasDown() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasLeft() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasRight() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsVisited() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetVisited() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCoordinate() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceLadder() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasLadder() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopyNode() {
		fail("Not yet implemented");
	}

}
