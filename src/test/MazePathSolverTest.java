package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import maze.Coordinate;
import maze.Maze;
import maze.MazeBuilder;
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
	/**
	 * good data
	 * structural basis
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testCanBeComplete_nominal() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coordinate entrance = new Coordinate(0,0);
		Coordinate exit = new Coordinate(2,3);
		MazeBuilder builder = new MazeBuilder(4);
		Maze maze = builder.toMaze();
		assertTrue(reflectCanBeComplete(entrance, exit, maze));
	}
	
	/**
	 * bad data
	 * structural basis
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testCanBeComplete_illegalEntrance() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coordinate entrance = new Coordinate(5,0);
		Coordinate exit = new Coordinate(2,3);
		MazeBuilder builder = new MazeBuilder(4);
		Maze maze = builder.toMaze();
		assertFalse(reflectCanBeComplete(entrance, exit, maze));
	}
	
	/**
	 * bad data
	 * structural basis
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testCanBeComplete_illegalExit() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coordinate entrance = new Coordinate(0,0);
		Coordinate exit = new Coordinate(2,9);
		MazeBuilder builder = new MazeBuilder(4);
		Maze maze = builder.toMaze();
		assertFalse(reflectCanBeComplete(entrance, exit, maze));
	}
	private boolean reflectCanBeComplete(Coordinate entrance, Coordinate exit, Maze maze) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("canBeComplete", Coordinate.class, Coordinate.class, Maze.class);
		method.setAccessible(true);
		return (boolean)method.invoke(solver,entrance, exit, maze);
	}
	
	/**
	 * cases for breadthFirstSearch
	 * structural basis+good data
	 * 1. nominal case
	 * 2. queue is empty. can't actually happen since entrance is checked to see
	 * if the maze can be completed
	 * 2. exit not found
	 * 
	 * bad data is already handled by can be complete and null checks
	 * 
	 * no (compound) boundary dealt in this method
	 * either path is found or not
	 * 
	 * data flow
	 * shortest path is defined, calculated, and returned
	 */
	/**
	 * Structural basis
	 * good data
	 * data flow
	 * 
	 * array index out of bounds exception
	 * this is caused by the fact that there is no check in addAdjacentPlanks method
	 * for checking if the adjacent node actually exists in the maze
	 * before putting it in the queue
	 * 
	 * This is a severe problem and can be fixed by calling maze's within bounds method
	 * 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testBreadthFirstSearch_nominal() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		MazeBuilder builder = new MazeBuilder(5);
		Coordinate entrance = new Coordinate(0, 0);
		Coordinate exit = new Coordinate(4, 4);
		
		placeLadderStraight(MazeDirection.up, entrance, builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 4), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 2), builder, 2);
		placeLadderStraight(MazeDirection.up, new Coordinate(2, 2), builder, 2);
		
		Maze maze = builder.toMaze();
		List<MazeNode> expected = new ArrayList<MazeNode>();
		expected.add(maze.getNode(new Coordinate(0, 0)));
		expected.add(maze.getNode(new Coordinate(0, 1)));
		expected.add(maze.getNode(new Coordinate(0, 2)));
		expected.add(maze.getNode(new Coordinate(0, 3)));
		expected.add(maze.getNode(new Coordinate(0, 4)));
		expected.add(maze.getNode(new Coordinate(1, 4)));
		expected.add(maze.getNode(new Coordinate(2, 4)));
		expected.add(maze.getNode(new Coordinate(3, 4)));
		expected.add(maze.getNode(new Coordinate(4, 4)));

		assertEquals(expected, reflectBreadthFirstSearch(maze, entrance, exit));
	}
	
	/**
	 * structural basis
	 * good data
	 * 
	 * This suffers the same problem as the nominal case where
	 * addAdjacentPlanks will add nodes to the queue without validating that it's in the maze
	 * 
	 * 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	
	@Test
	public void testBreadthFirstSearch_noExit() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MazeBuilder builder = new MazeBuilder(5);
		Coordinate entrance = new Coordinate(0, 0);
		Coordinate exit = new Coordinate(4, 4);
		Maze maze = builder.toMaze();
		
		assertEquals(null, reflectBreadthFirstSearch(maze, entrance, exit));
	}
	
	private void placeLadderStraight (MazeDirection direction, Coordinate source,
			MazeBuilder builder, int num){
		Coordinate ptr = source;
		for (int i = 0; i<num; i++){
			builder.placeLadder(direction, ptr);
			ptr.getAdjacentCoordinate(direction);
		}
	}

	private List<MazeNode> reflectBreadthFirstSearch(Maze maze, Coordinate entrance,
			Coordinate exit) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("breadthFirstSearch", Maze.class, Coordinate.class, Coordinate.class);
		method.setAccessible(true);
		return (List<MazeNode>)method.invoke(solver,maze,entrance,exit);
	}

	/**
	 * cases for createMinPath
	 * 1. nominal case -> minPath made out of prevMap
	 * 2. node entered is null -> no path found out of all the maze states
	 * 
	 * Data flow minPath is created out of prevMap
	 * is the minPath in the same order as the prevs in prevMap?
	 * 
	 * no (compound) boundary here
	 * 
	 * bad data it's already checked for in other methods
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * 
	 * 
	 */
	/**
	 * structural basis
	 * good data
	 * data flow
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testCreateMinPath_nominal() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		MazeBuilder builder = new MazeBuilder(5);

		placeLadderStraight(MazeDirection.up, new Coordinate(0,0), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 4), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 2), builder, 2);
		placeLadderStraight(MazeDirection.up, new Coordinate(2, 2), builder, 2);
		Maze maze = builder.toMaze();
		
		HashMap<MazeNode,MazeNode> prevMap = new HashMap<MazeNode,MazeNode>();
		MazeNode node = maze.getNode(new Coordinate(4, 4));
		
		prevMap.put(maze.getNode(new Coordinate(0, 1)), maze.getNode(new Coordinate(0, 0)));
		prevMap.put(maze.getNode(new Coordinate(0, 2)), maze.getNode(new Coordinate(0, 1)));
		prevMap.put(maze.getNode(new Coordinate(0, 3)), maze.getNode(new Coordinate(0, 2)));
		prevMap.put(maze.getNode(new Coordinate(0, 4)), maze.getNode(new Coordinate(0, 3)));
		prevMap.put(maze.getNode(new Coordinate(1, 4)), maze.getNode(new Coordinate(0, 4)));
		prevMap.put(maze.getNode(new Coordinate(2, 4)), maze.getNode(new Coordinate(1, 4)));
		prevMap.put(maze.getNode(new Coordinate(3, 4)), maze.getNode(new Coordinate(2, 4)));
		prevMap.put(maze.getNode(new Coordinate(4, 4)), maze.getNode(new Coordinate(3, 4)));
		
		List<MazeNode> expected = new ArrayList<MazeNode>();
		expected.add(maze.getNode(new Coordinate(4,4)));
		expected.add(maze.getNode(new Coordinate(3,4)));
		expected.add(maze.getNode(new Coordinate(2,4)));
		expected.add(maze.getNode(new Coordinate(1,4)));
		expected.add(maze.getNode(new Coordinate(0,4)));
		expected.add(maze.getNode(new Coordinate(0,3)));
		expected.add(maze.getNode(new Coordinate(0,2)));
		expected.add(maze.getNode(new Coordinate(0,1)));
		expected.add(maze.getNode(new Coordinate(0,0)));
		//preserves the order of visitedbut in reverse direction
		
		assertEquals(expected,reflectCreateMinPath(prevMap, node));
	}
	
	/**
	 * good data
	 * structural basis
	 * 
	 * if while resolves to false
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCreateMinPath_nullNode() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		HashMap<MazeNode,MazeNode> prevMap = new HashMap<MazeNode,MazeNode>();
		MazeNode node = null;
		
		assertEquals(new ArrayList<MazeNode>(), reflectCreateMinPath(prevMap, node));
	}
	
	private List<MazeNode> reflectCreateMinPath(HashMap<MazeNode, MazeNode> prevMap, 
			MazeNode node) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = MazePathSolver.class.getDeclaredMethod
				("createMinPath", HashMap.class, MazeNode.class);
		method.setAccessible(true);
		return (List<MazeNode>)method.invoke(solver,prevMap,node);
	}
	
	/**
	 * cases for addAdjcaentPlanks
	 * 1. nominal case 
	 * 2. for condition is false -> not possible MazeDirection is defined to have 4 directions
	 * 2. MazeNode is at the edge of the maze -> some of its adjacents are not in the maze
	 * 
	 * Data flow	prevMap is updated as nodes are added to the queue
	 * consider data flow for cases
	 * 
	 * (compound) boundary	there should be one, but not currently enforced
	 * 
	 * bad data	cannot happen since they are checked within the caller method
	 * or they are initialized in the caller method
	 */
	/**
	 * structural basis
	 * good data
	 * data flow
	 * 
	 * this test fails because addAdjacentPlanks does not check
	 * if there is a plank towards an adjacent node
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testAddAdjacentPlanks_nominal() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MazeBuilder builder = new MazeBuilder(5);

		placeLadderStraight(MazeDirection.up, new Coordinate(0,0), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 4), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 2), builder, 2);
		placeLadderStraight(MazeDirection.up, new Coordinate(2, 2), builder, 2);
		Maze maze = builder.toMaze();
		
		Queue<MazeNode> queue = new LinkedList<MazeNode>();
		HashMap<MazeNode, MazeNode> prevMap = new HashMap<MazeNode,MazeNode>();
		MazeNode node = maze.getNode(new Coordinate(2, 2));
		//two ladders by this node
		reflectAddAdjacentPlanks(maze, queue, prevMap, node);
		
		assertEquals(2, queue.size());
		assertEquals(2, prevMap.size());
	}

	/**
	 * boundary
	 * data flow
	 * good data
	 * 
	 * ArrayIndexOutOfBoundsException since 
	 * addAdjacentPlanks tries to add nodes that are not in the maze
	 * This could be fixed by checking if a coordinate is withinBounds first
	 * 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testAddAdjacentPlanks_outsideMaze() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MazeBuilder builder = new MazeBuilder(5);

		placeLadderStraight(MazeDirection.up, new Coordinate(0,0), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 4), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 2), builder, 2);
		placeLadderStraight(MazeDirection.up, new Coordinate(2, 2), builder, 2);
		Maze maze = builder.toMaze();

		Queue<MazeNode> queue = new LinkedList<MazeNode>();
		HashMap<MazeNode, MazeNode> prevMap = new HashMap<MazeNode,MazeNode>();
		MazeNode node = maze.getNode(new Coordinate(0, 0));
		
		//one ladder by this node
		reflectAddAdjacentPlanks(maze, queue, prevMap, node);
		assertEquals(1, queue.size());
		assertEquals(1, prevMap.size());
	}
	
	private void reflectAddAdjacentPlanks(Maze maze, Queue<MazeNode> queue,
			HashMap<MazeNode, MazeNode> prevMap, MazeNode node) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MazePathSolver.class.getDeclaredMethod
				("addAdjacentPlanks", Maze.class, Queue.class, HashMap.class, MazeNode.class);
		method.setAccessible(true);
		method.invoke(solver,maze,queue,prevMap,node);
	}

	/**
	 * cases for canPlacePlank
	 * 1. nominal case (within bounds, isn't visited, doesn't have ladder)
	 * 2. not within bounds
	 * 3. is visited
	 * 4. has ladder
	 * 
	 * no data flow involved
	 * no (compound) boundary involved
	 * 
	 * bad data not possible
	 * since the caller methods initializes or checks the parameters
	 * 
	 *  
	 */
	/**
	 * structural basis
	 * good data
	 * 
	 * there is a mistake in the and logic where
	 * instead of "and does not have ladder"
	 * a mistake was made and the "not" was taken out
	 * This by one character error can be fixed by adding the not to has ladder
	 *  
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testCanPlacePlank_nominal() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MazeBuilder builder = new MazeBuilder(5);

		placeLadderStraight(MazeDirection.up, new Coordinate(0,0), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 4), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 2), builder, 2);
		placeLadderStraight(MazeDirection.up, new Coordinate(2, 2), builder, 2);
		Maze maze = builder.toMaze();

		assertTrue(reflectCanPlacePlank(maze, new Coordinate(0,0), MazeDirection.right));
	}
	
	/**
	 * good data
	 * structural basis
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testCanPlacePlank_notWithinBounds() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MazeBuilder builder = new MazeBuilder(5);

		placeLadderStraight(MazeDirection.up, new Coordinate(0,0), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 4), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 2), builder, 2);
		placeLadderStraight(MazeDirection.up, new Coordinate(2, 2), builder, 2);
		Maze maze = builder.toMaze();
		
		assertFalse(reflectCanPlacePlank(maze, new Coordinate(0, 0), MazeDirection.down));
	}
	
	/**
	 * good data
	 * structural basis
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testCanPlacePlank_isVisited() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MazeBuilder builder = new MazeBuilder(5);

		placeLadderStraight(MazeDirection.up, new Coordinate(0,0), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 4), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 2), builder, 2);
		placeLadderStraight(MazeDirection.up, new Coordinate(2, 2), builder, 2);
		Maze maze = builder.toMaze();
		
		maze.getNode(new Coordinate(0, 2)).setVisited(true);
		assertFalse(reflectCanPlacePlank(maze, new Coordinate(0, 1), MazeDirection.up));
	}
	
	/**
	 * good data
	 * structural basis
	 * 
	 * this case is where the mistake is at
	 * the not character really should be added to fix this logic
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testCanPlacePlank_hasLadder() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MazeBuilder builder = new MazeBuilder(5);

		placeLadderStraight(MazeDirection.up, new Coordinate(0,0), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 4), builder, 4);
		placeLadderStraight(MazeDirection.right, new Coordinate(0, 2), builder, 2);
		placeLadderStraight(MazeDirection.up, new Coordinate(2, 2), builder, 2);
		Maze maze = builder.toMaze();
		
		assertFalse(reflectCanPlacePlank(maze, new Coordinate(0, 0), MazeDirection.up));
	}
	
	
	private boolean reflectCanPlacePlank(Maze maze, Coordinate toVisitCoord, 
			MazeDirection direction) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("canPlacePlank", Maze.class, Coordinate.class, MazeDirection.class);
		method.setAccessible(true);
		return (boolean)method.invoke(solver,maze,toVisitCoord,direction);
	}
}
