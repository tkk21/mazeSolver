package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import maze.Coordinate;
import maze.MazeDirection;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	Coordinate coordinate;
	Coordinate expected;
	
	@Before
	public void reset(){
		coordinate = null;
		expected = null;
	}
	
	/**
	 * Structured Basis 
	 * Good data
	 */
	@Test
	public void testCoordinate_Origin() {
		coordinate = new Coordinate(0, 0);
		assertEquals(0, coordinate.getX());
		assertEquals(0, coordinate.getY());
	}	
	
	
	/**
	 * Structured Basis
	 */
	@Test
	public void testGetX() {
		coordinate = new Coordinate(2, 0);
		assertEquals(2, coordinate.getX());
	}

	/**
	 * Structured Basis
	 */
	@Test
	public void testGetY() {
		coordinate = new Coordinate(0, 2);
		assertEquals(2, coordinate.getY());
	}

	/**
	 * Structured Basis
	 * Good data 
	 */
	@Test
	public void testIsFirstQuadrant_FirstQuadrant() {
		coordinate = new Coordinate(2, 3);
		assertTrue(coordinate.isFirstQuadrant());
	}
	
	/**
	 * Structured Basis 
	 * Bad data
	 * 
	 * The Coordinate class itself does not care
	 * if a coordinate is in the first quadrant or not
	 * but the Coordinate class will still keep track of that
	 * so that other classes that use coordinate can know
	 * if a coordinate is in the first coordinate or not
	 */
	@Test
	public void testIsFirstQuadrant_NotFirstQuadrant() {
		coordinate = new Coordinate(-4, -1);
		assertFalse(coordinate.isFirstQuadrant());
	}
	
	/**
	 * Structured Basis
	 * Good data
	 * 
	 * even though mathematically origin isn't really part of first quadrant
	 * maze array includes (0,0) so this results as true for sake of simplicity 
	 */
	@Test
	public void testIsFirstQuadrant_Origin() {
		coordinate = new Coordinate(0, 0);
		assertTrue(coordinate.isFirstQuadrant());
	}

	/**
	 * Structured Basis
	 */
	@Test
	public void testGetUpCoordinate() {
		coordinate = new Coordinate (2,2);
		expected = new Coordinate(2, 3);
		assertEquals(expected, coordinate.getUpCoordinate());
	}

	/**
	 * Structured Basis
	 */
	@Test
	public void testGetDownCoordinate() {
		coordinate = new Coordinate(2, 2);
		expected = new Coordinate(2, 1);
		assertEquals(expected, coordinate.getDownCoordinate());
	}

	/**
	 * Structured Basis
	 */
	@Test
	public void testGetLeftCoordinate() {
		coordinate = new Coordinate(2, 2);
		expected = new Coordinate(1, 2);
		assertEquals(expected, coordinate.getLeftCoordinate());
	}

	/**
	 * Structured Basis
	 */
	@Test
	public void testGetRightCoordinate() {
		coordinate = new Coordinate(2, 2);
		expected = new Coordinate(3, 2);
		assertEquals(expected, coordinate.getRightCoordinate());
	}

	/**
	 * Structured Basis
	 * Good data
	 */
	@Test
	public void testGetAdjacentCoordinate_up() {
		coordinate = new Coordinate(2,2);
		expected = new Coordinate(2,3);
		assertEquals(expected, coordinate.getAdjacentCoordinate(MazeDirection.up));
	}
	
	/**
	 * Structured Basis
	 * Good data
	 */
	@Test
	public void testGetAdjacentCoordinate_down() {
		coordinate = new Coordinate(2,2);
		expected = new Coordinate(2,1);
		assertEquals(expected, coordinate.getAdjacentCoordinate(MazeDirection.down));
	}
	
	/**
	 * Structured Basis
	 * Good data
	 */
	@Test
	public void testGetAdjacentCoordinate_left() {
		coordinate = new Coordinate(2,2);
		expected = new Coordinate(1,2);
		assertEquals(expected, coordinate.getAdjacentCoordinate(MazeDirection.left));
	}
	
	/**
	 * Structured Basis
	 * Good data
	 */
	@Test
	public void testGetAdjacentCoordinate_right() {
		coordinate = new Coordinate(2,2);
		expected = new Coordinate(3,2);
		assertEquals(expected, coordinate.getAdjacentCoordinate(MazeDirection.right));
	}
	
	/**
	 * Structured Basis
	 * Bad data
	 */
	@Test(expected = NullPointerException.class)
	public void testGetAdjacentCoordinate_badData() {
		coordinate = new Coordinate(2,2);
		expected = new Coordinate(2,3);
		assertEquals(expected, coordinate.getAdjacentCoordinate(null));
	}

	/**
	 * cases for equals method
	 * 1. nominal case c!=null and x==x and y==y
	 * 2. c==null
	 * 3. X!=X
	 * 4. Y!=Y
	 */
	/**
	 * Structured Basis
	 * Good data
	 */
	@Test
	public void testEqualsObject_nominal() {
		coordinate = new Coordinate(2, 2);
		assertTrue(coordinate.equals(new Coordinate(2, 2)));
	}
	
	/**
	 * Structured Basis
	 * Bad data
	 */
	@Test
	public void testEqualsObject_null(){
		coordinate = new Coordinate(2, 2);
		assertFalse(coordinate.equals(null));
	}

	/**
	 * Structured Basis
	 * Good data
	 */
	@Test
	public void testEqualsObject_xNotEqual() {
		coordinate = new Coordinate(2, 2);
		assertFalse(coordinate.equals(new Coordinate(1, 2)));
	}
	
	/**
	 * Structured Basis
	 * Good data
	 */
	@Test
	public void testEqualsObject_yNotEqual() {
		coordinate = new Coordinate(2, 2);
		assertFalse(coordinate.equals(new Coordinate(2, 3)));
	}
	
	/**
	 * the case for null == null cannot exist
	 * this is because a Coordinate object has to call equals method and
	 * a null object cannot call a method
	 */
	
	
	/**
	 * cases for castToCoordinate method
	 * 1. nominal case, o != null and o is instance of Coordinate
	 * 2. o == null
	 * 3. o isn't instance of Coordinate
	 */
	
	/**
	 * Structured Basis
	 * Good data
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testCastToCoordinate_nominal() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//realized that castToCoordinate could have been a static method
		coordinate = new Coordinate(1, 1);
		Object o = new Coordinate (2,2);
		assertEquals(new Coordinate(2, 2), reflectCastToCoordinate(coordinate, o));
	}
	
	/**
	 * Structured Basis
	 * Good data
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testCastToCoordinate_oIsNull() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//realized that castToCoordinate could have been a static method
		coordinate = new Coordinate(1, 1);
		Object o = null;
		assertEquals(null, reflectCastToCoordinate(coordinate, o));
	}
	
	/**
	 * Structured Basis
	 * Bad data
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testCastToCoordinate_notCoordinate() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//realized that castToCoordinate could have been a static method
		coordinate = new Coordinate(1, 1);
		Object o = "(2, 2)";
		assertEquals(null, reflectCastToCoordinate(coordinate, o));
	}
	
	
	private Coordinate reflectCastToCoordinate(Coordinate coordinate, Object o) 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = Coordinate.class.getDeclaredMethod
				("castToCoordinate", Object.class);
		method.setAccessible(true);
		return (Coordinate)method.invoke(coordinate, o);
	}
}
