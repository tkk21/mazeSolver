package test;

import static org.junit.Assert.*;
import maze.MazeDirection;

import org.junit.Test;

public class MazeDirectionTest {

	/**
	 * Structured Basis test case
	 */
	@Test
	public void testOpposite_up() {
		MazeDirection direction = MazeDirection.up;
		MazeDirection actual = direction.opposite();
		assertEquals(MazeDirection.down, actual);
	}
	
	/**
	 * Structured Basis test case
	 */
	@Test
	public void testOpposite_down() {
		MazeDirection direction = MazeDirection.down;
		MazeDirection actual = direction.opposite();
		assertEquals(MazeDirection.up, actual);
	}
	
	/**
	 * Structured Basis test case
	 */
	@Test
	public void testOpposite_left() {
		MazeDirection direction = MazeDirection.left;
		MazeDirection actual = direction.opposite();
		assertEquals(MazeDirection.right, actual);
	}
	
	/**
	 * Structured Basis test case
	 */
	@Test
	public void testOpposite_right() {
		MazeDirection direction = MazeDirection.right;
		MazeDirection actual = direction.opposite();
		assertEquals(MazeDirection.left, actual);
	}
	
	/**
	 * Structured Basis, Bad data
	 */
	@Test(expected = NullPointerException.class)
	public void testOpposite_badData() {
		MazeDirection direction = null;
		MazeDirection actual = direction.opposite();
		assertEquals(null, actual);
	}
}
