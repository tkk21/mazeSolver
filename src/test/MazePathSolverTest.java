package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import maze.Coordinate;
import maze.Maze;
import maze.MazeDirection;
import maze.MazeNode;
import maze.MazePathSolver;

import org.junit.Before;
import org.junit.Test;

public class MazePathSolverTest {

	List<MazeNode> minPath;
	List<MazeNode> plankPath;
	MazePathSolver solver;
	
	@Before
	public void init (){
		minPath = new ArrayList<MazeNode>();
		plankPath = new ArrayList<MazeNode>();
		solver = new MazePathSolver();
	}
	/**
	 * things to test
	 * 1. terrible stress test since this solve is O(N^4)
	 * 
	 * may need to make some helpers
	 * external text file based builder?
	 * 
	 * Data flow tests
	 * Test for Defined-Use cases
	 * use helper methods to see what is expected
	 * 
	 * compound boundaries, when there's a comparison
	 * 	eg. x>1 -> 1. x<1 2. x=1 3. x>1
	 */
	@Test
	public void testShortestPlankPath() {
		fail("Not yet implemented");
	}
	
	/**
	 * cases for comparePath
	 * goes through if 
	 * 1. plankPath != null and plankPath.size<minPath.size -> plankPath becomes minPath
	 * 2. plankPath != null and minPath == null -> plankPath becomes minPath by default
	 * doesn't go through if
	 * 3. plankPath == null -> plankPath never found
	 * 4. minPath != null and minPath.size<plankPath.size -> minPath smaller than plankPath
	 * 
	 * bad data
	 * 5. null plankPath and null minPath
	 * 
	 * no data flow, nothing's being changed just compared
	 * 
	 * boundary
	 * 6. when minPath.size == plankPath.size
	 * 
	 * compound boundary doesn't really apply well here since this is comparison
	 * it does not matter if the comparison is between two big paths or with two small paths
	 */
	/**
	 * Structural Basis
	 * good data
	 *  boundary
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * 
	 */
	@Test
	public void testComparePath_plankWinsByComparison() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		minPath = createNodeList(4);
		plankPath = createNodeList(3);
		assertEquals(plankPath, reflectComparePath(minPath, plankPath));
	}
	
	/**
	 * Structural Basis
	 * good data
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * 
	 */
	@Test
	public void testComparePath_plankWinsByDefault() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		minPath = null;
		plankPath = createNodeList(3);
		assertEquals(plankPath, reflectComparePath(minPath, plankPath));
	}
	
	/**
	 * Structural Basis
	 * good data
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * 
	 */
	@Test
	public void testComparePath_plankPathNull() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		minPath = createNodeList(4);
		plankPath = null;
		assertEquals(minPath, reflectComparePath(minPath, plankPath));
	}
	
	/**
	 * Structural Basis
	 * good data
	 *  boundary
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * 
	 */
	@Test
	public void testComparePath_minPathWinsByComparison() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		minPath = createNodeList(4);
		plankPath = createNodeList(5);
		assertEquals(minPath, reflectComparePath(minPath, plankPath));
	}
	
	/**
	 * "bad data"
	 * 
	 * closest to bad data as this method can get
	 * However, this is actually a legitimate case
	 * example:
	 * a minPath is not found out of the maze states so far
	 * and the plankPath recently found also doesn't reach the exit
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testComparePath_nullPaths() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		minPath = null;
		plankPath = null;
		assertEquals(null, reflectComparePath(minPath, plankPath));
	}
	
	/**
	 * boundary
	 * 
	 * when they're both equal,
	 * minPath is picked over plankPath because minPath was earlier
	 * this has an implication that plankPath may be the shortest path
	 * without using any planks
	 * and that may arguably be better
	 * 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testComparePath_equalPaths() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		minPath = createNodeList(4);
		plankPath = createNodeList(4);
		assertEquals(minPath, reflectComparePath(minPath, plankPath));
	}

	private List<MazeNode> createNodeList(int size){
		ArrayList<MazeNode> list = new ArrayList<MazeNode>();
		for (int i = 0; i<size; i++){
			list.add(new MazeNode(new Coordinate(i, i)));
		}
		return list;
	}
	
	private List<MazeNode> reflectComparePath(List<MazeNode> minPath,
			List<MazeNode> plankPath) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("comparePath", List.class, List.class);
		method.setAccessible(true);
		return (List<MazeNode>)method.invoke(solver,minPath, plankPath);
	}

	/**
	 * cases for canBeComplete
	 * 1. nominal both are within bounds
	 * 2. entrance not within bounds
	 * 3. exit not within bounds
	 * 
	 * data flow does not apply since this is a check method
	 * (compound)boundary does not apply since this is a sequential check method
	 */
	@Test
	public void testCanBeComplete() {
		
	}
	private boolean reflectCanBeComplete(Coordinate entrance, Coordinate exit, Maze maze) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("canBeComplete", Coordinate.class, Coordinate.class, Maze.class);
		method.setAccessible(true);
		return (boolean)method.invoke(solver,entrance, exit, maze);
	}

	private List<MazeNode> reflectBreadthFirstSearch(Maze maze, Coordinate entrance,
			Coordinate exit) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("breadthFirstSearch", Maze.class, Coordinate.class, Coordinate.class);
		method.setAccessible(true);
		return (List<MazeNode>)method.invoke(solver,maze,entrance,exit);
	}

	private List<MazeNode> reflectCreateMinPath(HashMap<MazeNode, MazeNode> prevMap, 
			MazeNode node) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = MazePathSolver.class.getDeclaredMethod
				("createMinPath", HashMap.class, MazeNode.class);
		method.setAccessible(true);
		return (List<MazeNode>)method.invoke(solver,prevMap,node);
	}

	private void reflectAddAdjacentPlanks(Maze maze, Queue<MazeNode> queue,
			HashMap<MazeNode, MazeNode> prevMap, MazeNode node) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MazePathSolver.class.getDeclaredMethod
				("addAdjacentPlanks", Maze.class, Queue.class, HashMap.class, MazeNode.class);
		method.setAccessible(true);
		method.invoke(solver,maze,queue,prevMap,node);
	}

	private boolean reflectCanPlacePlank(Maze maze, Coordinate toVisitCoord, 
			MazeDirection direction) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("canPlacePlank", Maze.class, Coordinate.class, MazeDirection.class);
		method.setAccessible(true);
		return (boolean)method.invoke(solver,maze,toVisitCoord,direction);
	}
}
