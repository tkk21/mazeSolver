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
	 * no data flow (nothing's being changed), no boundaries, and no bad data(what's bad data for primitive boolean?)
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
	
	/**
	 * getCoordinate is a getter method that returns coordinate
	 * no data flow (nothing's changed just returned)
	 * boundaries are taken care of by other classes
	 */
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testGetCoordinate() {
		node = new MazeNode(new Coordinate(2, 3));
		assertEquals(new Coordinate(2,3), node.getCoordinate());
	}
	
	/**
	 * bad data
	 * 
	 * This is a problem and nothing is done about it
	 * a null coordinate would result in null pointer exception in many methods
	 * 
	 * A possible solution to this would be have the setting of the coordinate as
	 * a method separate from the constructor
	 * and having it be an invalid node
	 * which other classes using this MazeNode would pick up and handle properly
	 */
	@Test
	public void testGetCoordinate_nullCoordinate() {
		node = new MazeNode(null);
		assertEquals(null, node.getCoordinate());
	}

	/**
	 * can check for data flow for each case
	 * cases for placeLadder
	 * 1. up
	 * 2. down
	 * 3. left
	 * 4. right
	 * 5. null direction (bad data)
	 * 
	 * no boundaries involved
	 */
	/**
	 * structural basis
	 * good data
	 * data flow
	 */
	@Test
	public void testPlaceLadder_up() {
		boolean before = node.hasUp();
		node.placeLadder(MazeDirection.up);
		assertFalse(before == node.hasUp());
		assertTrue(node.hasUp());
	}
	
	/**
	 * structural basis
	 * good data
	 * data flow
	 */
	@Test
	public void testPlaceLadder_down() {
		boolean before = node.hasDown();
		node.placeLadder(MazeDirection.down);
		assertFalse(before == node.hasDown());
		assertTrue(node.hasDown());
	}
	
	/**
	 * structural basis
	 * good data
	 * data flow
	 */
	@Test
	public void testPlaceLadder_left() {
		boolean before = node.hasLeft();
		node.placeLadder(MazeDirection.left);
		assertFalse(before == node.hasLeft());
		assertTrue(node.hasLeft());
	}
	
	/**
	 * structural basis
	 * good data
	 * data flow
	 */
	@Test
	public void testPlaceLadder_right() {
		boolean before = node.hasRight();
		node.placeLadder(MazeDirection.right);
		assertFalse(before == node.hasRight());
		assertTrue(node.hasRight());
	}

	/**
	 * bad data
	 */
	@Test(expected=NullPointerException.class)
	public void testPlaceLadder_nullDirection() {
		node.placeLadder(null);
	}
	
	/**
	 * cases for hasLadder
	 * 1. up
	 * 2. down
	 * 3. left
	 * 4. right
	 * 5. null direction
	 * no boundaries involved
	 * no data flow involved
	 */
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasLadder_up() {
		node.placeLadder(MazeDirection.up);
		assertTrue(node.hasLadder(MazeDirection.up));
	}
	
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasLadder_down() {
		node.placeLadder(MazeDirection.down);
		assertTrue(node.hasLadder(MazeDirection.down));
	}
	
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasLadder_left() {
		node.placeLadder(MazeDirection.left);
		assertTrue(node.hasLadder(MazeDirection.left));
	}
	
	/**
	 * structural basis
	 * good data
	 */
	@Test
	public void testHasLadder_right() {
		node.placeLadder(MazeDirection.right);
		assertTrue(node.hasLadder(MazeDirection.right));
	}

	/**
	 * bad data
	 */
	@Test(expected=NullPointerException.class)
	public void testHasLadder_nullDirection() {
		node.hasLadder(null);
	}
	
	/**
	 * structural basis
	 * data flow (visited is not copied but everything else is)
	 * good data
	 * 
	 * no boundaries just sequential
	 * 
	 */
	@Test
	public void testCopyNode() {
		node.placeLadder(MazeDirection.up);
		node.placeLadder(MazeDirection.left);
		node.setVisited(true);
		
		MazeNode copy = MazeNode.copyNode(node);
		assertFalse(MazeTest.nodeEquals(node, copy));
		
		assertTrue(copy.hasUp());
		assertTrue(copy.hasLeft());
		assertFalse(copy.hasDown());
		assertFalse(copy.hasRight());
		
		assertFalse(copy.isVisited());
	}
	
	/**
	 * bad data
	 */
	@Test(expected=NullPointerException.class)
	public void testCopyNode_nullNode(){
		node.copyNode(null);
	}

	private void reflectSetUp(MazeNode node, boolean up) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeNode.class.getDeclaredMethod
				("setUp", boolean.class);
		method.setAccessible(true);
		method.invoke(node, up);
	}
	
	private void reflectSetDown(MazeNode node, boolean down) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeNode.class.getDeclaredMethod
				("setDown", boolean.class);
		method.setAccessible(true);
		method.invoke(node, down);
	}
	
	private void reflectSetLeft(MazeNode node, boolean left) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeNode.class.getDeclaredMethod
				("setLeft", boolean.class);
		method.setAccessible(true);
		method.invoke(node, left);
	}
	
	private void reflectSetRight(MazeNode node, boolean right) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeNode.class.getDeclaredMethod
				("setRight", boolean.class);
		method.setAccessible(true);
		method.invoke(node, right);
	}
}