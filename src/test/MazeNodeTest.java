package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	 * hasUp, hasDown, hasLeft, hasRight, isVisited
	 * are getter methods
	 * no data flow, no boundaries, and no bad data
	 * even if bad data is stored in the field,
	 * that is the job of other methods to check
	 */
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

	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasDown() {
		node.placeLadder(MazeDirection.down);
		assertTrue(node.hasDown());
	}
	
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasDown_false() {
		assertFalse(node.hasDown());
	}

	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasLeft() {
		node.placeLadder(MazeDirection.left);
		assertTrue(node.hasLeft());
	}
	
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasLeft_false() {
		assertFalse(node.hasLeft());
	}

	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasRight() {
		node.placeLadder(MazeDirection.right);
		assertTrue(node.hasRight());
	}

	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasRight_false() {
		assertFalse(node.hasRight());
	}
	
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testIsVisited() {
		node.setVisited(true);
		assertTrue(node.isVisited());
	}
	
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testIsVisited_false() {
		assertFalse(node.isVisited());
	}

	/**
	 * setVisited, setUp, setDown, setLeft, setRight
	 * are setter methods
	 * no boundaries, no bad data (what is a bad primitive boolean?)
	 *  
	 */
	/**
	 * structural basis
	 * good data
	 * data flow
	 */
	@Test
	public void testSetVisited() {
		boolean before = node.isVisited();
		node.setVisited(true);
		assertFalse(node.isVisited() == before);
		assertTrue(node.isVisited());
	}
	
	/**
	 * structural basis
	 * good data
	 * data flow
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testSetUp() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean before = node.hasUp();
		reflectSetUp(node, true);
		assertFalse(node.hasUp() == before);
		assertTrue(node.hasUp());
	}
	
	/**
	 * structural basis
	 * good data
	 * data flow
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testSetDown() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean before = node.hasDown();
		reflectSetDown(node, true);
		assertFalse(node.hasDown() == before);
		assertTrue(node.hasDown());
	}
	
	/**
	 * structural basis
	 * good data
	 * data flow
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testSetLeft() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean before = node.hasLeft();
		reflectSetLeft(node, true);
		assertFalse(node.hasLeft() == before);
		assertTrue(node.hasLeft());
	}
	
	/**
	 * structural basis
	 * good data
	 * data flow
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testSetRight() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean before = node.hasRight();
		reflectSetRight(node, true);
		assertFalse(node.hasRight() == before);
		assertTrue(node.hasRight());
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

	private void reflectSetUp(MazeNode node, boolean up) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeNode.class.getDeclaredMethod
				("setUp", Boolean.class);
		method.setAccessible(true);
		method.invoke(node, up);
	}
	
	private void reflectSetDown(MazeNode node, boolean down) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeNode.class.getDeclaredMethod
				("setDown", Boolean.class);
		method.setAccessible(true);
		method.invoke(node, down);
	}
	
	private void reflectSetLeft(MazeNode node, boolean left) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeNode.class.getDeclaredMethod
				("setLeft", Boolean.class);
		method.setAccessible(true);
		method.invoke(node, left);
	}
	
	private void reflectSetRight(MazeNode node, boolean right) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeNode.class.getDeclaredMethod
				("setRight", Boolean.class);
		method.setAccessible(true);
		method.invoke(node, right);
	}
}