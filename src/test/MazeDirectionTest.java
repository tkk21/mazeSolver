package test;

import static org.junit.Assert.*;
import maze.MazeDirection;

import org.junit.Test;

public class MazeDirectionTest {

	/**
	 * Structured Basis test case
	 */
	@Test
	public void testOpposite_Up() {
		MazeDirection direction = MazeDirection.up;
		MazeDirection actual = direction.opposite();
		assertEquals(MazeDirection.down, actual);
	}
	
	/**
	 * Structured Basis test case
	 */
	@Test
	public void testOpposite_Down() {
		MazeDirection direction = MazeDirection.down;
		MazeDirection actual = direction.opposite();
		assertEquals(MazeDirection.up, actual);
	}
	
	/**
	 * Structured Basis test case
	 */
	@Test
	public void testOpposite_Left() {
		MazeDirection direction = MazeDirection.left;
		MazeDirection actual = direction.opposite();
		assertEquals(MazeDirection.right, actual);
	}
	
	/**
	 * Structured Basis test case
	 */
	@Test
	public void testOpposite_Right() {
		MazeDirection direction = MazeDirection.right;
		MazeDirection actual = direction.opposite();
		assertEquals(MazeDirection.left, actual);
	}
	
	/**
	 * Structured Basis, Bad data
	 */
	@Test(expected = NullPointerException.class)
	public void testOpposite_BadData() {
		MazeDirection direction = null;
		MazeDirection actual = direction.opposite();
		assertEquals(null, actual);
	}
}
