package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import maze.Coordinate;
import maze.Maze;
import maze.MazeDirection;
import maze.MazeNode;
import maze.MazePathSolver;

import org.junit.Test;

public class MazePathSolverTest {

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
	 * 5. null plankPath
	 * 6. null minPath
	 * 
	 * no data flow, nothing's being changed just compared
	 * 
	 * boundary
	 * 7. when minPath.size == plankPath.size
	 * 
	 */
	/**
	 * Structural Basis
	 * good data
	 * compound boundary
	 * 
	 */
	@Test
	public void testComparePath_plankWinsByComparison() {
		
	}


	private List<MazeNode> reflectComparePath(List<MazeNode> minPath,
			List<MazeNode> plankPath) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("comparePath", List.class, List.class);
		method.setAccessible(true);
		return (List<MazeNode>)method.invoke(minPath, plankPath);
	}

	private boolean reflectCanBeComplete(Coordinate entrance, Coordinate exit, Maze maze) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("canBeComplete", Coordinate.class, Coordinate.class, Maze.class);
		method.setAccessible(true);
		return (boolean)method.invoke(entrance, exit, maze);
	}

	private List<MazeNode> reflectBreadthFirstSearch(Maze maze, Coordinate entrance,
			Coordinate exit) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("breadthFirstSearch", Maze.class, Coordinate.class, Coordinate.class);
		method.setAccessible(true);
		return (List<MazeNode>)method.invoke(maze,entrance,exit);
	}

	private List<MazeNode> reflectCreateMinPath(HashMap<MazeNode, MazeNode> prevMap, 
			MazeNode node) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = MazePathSolver.class.getDeclaredMethod
				("createMinPath", HashMap.class, MazeNode.class);
		method.setAccessible(true);
		return (List<MazeNode>)method.invoke(prevMap,node);
	}

	private void reflectAddAdjacentPlanks(Maze maze, Queue<MazeNode> queue,
			HashMap<MazeNode, MazeNode> prevMap, MazeNode node) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MazePathSolver.class.getDeclaredMethod
				("addAdjacentPlanks", Maze.class, Queue.class, HashMap.class, MazeNode.class);
		method.setAccessible(true);
		method.invoke(maze,queue,prevMap,node);
	}

	private boolean reflectCanPlacePlank(Maze maze, Coordinate toVisitCoord, 
			MazeDirection direction) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = MazePathSolver.class.getDeclaredMethod
				("canPlacePlank", Maze.class, Coordinate.class, MazeDirection.class);
		method.setAccessible(true);
		return (boolean)method.invoke(maze,toVisitCoord,direction);
	}
}
