package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import maze.Coordinate;
import maze.Maze;
import maze.MazeBuilder;
import maze.MazeDirection;
import maze.MazeNode;

import org.junit.Before;
import org.junit.Test;

public class MazeBuilderTest {

	MazeBuilder builder;
	
	@Before
	public void reset(){
		builder = null;
	}
	
	/**
	 * Structural basis
	 * good data
	 */
	@Test
	public void testMazeBuilder_positiveSize() {
		builder = new MazeBuilder(3);
		assertEquals(3, builder.toMaze().size());
	}
	
	/**
	 * Bad data
	 */
	@Test(expected = NegativeArraySizeException.class)
	public void testMazeBuilder_negativeSize() {
		builder = new MazeBuilder(-6);
		assertEquals(-6, builder.toMaze().size());
	}
	
	/**
	 * cases for buildNodes
	 * 1. nominal case
	 * 2. initial for condition is false (size<1)
	 * 3. initial for condition is false (size<1)
	 */
	
	/**
	 *Structural Basis
	 *Data flow (goes from an array of null nodes to an array of nodes) 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testBuildNodes_nominal() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		builder = new MazeBuilder(3);
		reflectBuildNodes(builder);
		Maze maze = builder.toMaze();
		for (int i = 0; i<maze.size(); i++){
			for (int j = 0; j<maze.size(); j++){
				//if true that means a node did not get initialized
				assertFalse(maze.getNode(new Coordinate(i, j))==null);
			}
		}
	}
	
	/**
	 * Structural basis
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testBuildNodes_falseInitCond() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		builder = new MazeBuilder(0);
		reflectBuildNodes(builder);
		/**
		 * no way to check if something changed
		 * could have had a field for nodeCount to help test this
		 */
		
	}

	/**
	 * Structural basis
	 * Good data
	 * Data flow
	 */
	@Test
	public void testPlaceLadder_withinBound() {
		builder = new MazeBuilder(4);
		MazeBuilder before = new MazeBuilder(4);
		before.copyMaze(builder.toMaze());
		builder.placeLadder(MazeDirection.up, new Coordinate(0, 0));
		assertTrue(
				builder.toMaze().getNode(new Coordinate(0, 0)).
				hasLadder(MazeDirection.up));
		assertTrue(
				builder.toMaze().getNode(new Coordinate(0, 1)).
				hasLadder(MazeDirection.down)
				);
		assertFalse(MazeTest.nodeEquals(
				before.toMaze().getNode(new Coordinate(0, 0)),
				builder.toMaze().getNode(new Coordinate(0, 0))));
	}
	
	/**
	 * Bad data
	 */
	@Test
	public void testPlaceLadder_outsideMaze(){
		builder = new MazeBuilder(4);
		builder.placeLadder(MazeDirection.down, new Coordinate(0, 0));
	}
	
	/**
	 * Bad data
	 */
	@Test(expected=NullPointerException.class)
	public void testPlaceLadder_nullCoordinate(){
		builder = new MazeBuilder(4);
		builder.placeLadder(MazeDirection.up, null);
	}
	
	/**
	 * Bad data
	 */
	@Test(expected=NullPointerException.class)
	public void testPlaceLadder_nullDirection(){
		builder = new MazeBuilder(4);
		builder.placeLadder(null, new Coordinate(0, 0));
	}

	/**
	 * Structural basis
	 * Good data
	 */
	@Test
	public void testCopyMaze_nominal() {
		builder = new MazeBuilder(4);
		builder.placeLadder(MazeDirection.left, new Coordinate(2, 2));
		Maze maze = builder.toMaze();
		MazeBuilder copy = new MazeBuilder(4);
		copy.copyMaze(maze);
		Maze copiedMaze = copy.toMaze();
		for (int i = 0; i<maze.size();i++){
			for (int j = 0; j<maze.size();j++){
				assertTrue(MazeTest.nodeEquals(maze.getNode(new Coordinate(i, j)),
						copiedMaze.getNode(new Coordinate(i, j))));
			}
		}
	}
	
	/**
	 * bad data
	 * 
	 * this is a really bad behavior 
	 * changes should be made so that the copy maze cannot declare its own size
	 * either that or the copy operation should create a new maze
	 */
	@Test
	public void testCopyMaze_wrongSizeForCopy(){
		builder = new MazeBuilder(4);
		MazeBuilder copy = new MazeBuilder(3);
		copy.copyMaze(builder.toMaze());
	}

	/**
	 * Structural basis
	 */
	@Test
	public void testToMaze() {
		builder = new MazeBuilder(4);
		Maze maze = builder.toMaze();
		for (int i = 0; i<maze.size(); i++){
			for (int j = 0; j<maze.size(); j++){
				MazeTest.nodeEquals(maze.getNode(new Coordinate(i, j)),
						new MazeNode(new Coordinate(i, j)));
			}
		}
	}

	private void reflectBuildNodes(MazeBuilder builder) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeBuilder.class.getDeclaredMethod
				("buildNodes", null);
		method.setAccessible(true);
		method.invoke(builder, null);
	}
}
