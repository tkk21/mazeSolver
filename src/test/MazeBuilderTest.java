package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import maze.Coordinate;
import maze.Maze;
import maze.MazeBuilder;

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
	@Test(expected = ArrayIndexOutOfBoundsException.class)
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
	
	

	@Test
	public void testPlaceLadder() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopyMaze() {
		fail("Not yet implemented");
	}

	@Test
	public void testToMaze() {
		fail("Not yet implemented");
	}

	private void reflectBuildNodes(MazeBuilder builder) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazeBuilder.class.getDeclaredMethod
				("buildNodes", null);
		method.setAccessible(true);
		method.invoke(builder, null);
	}
}
