package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import maze.Coordinate;
import maze.Maze;
import maze.MazeDirection;
import maze.MazeNode;

import org.junit.Test;

public class MazePathSolverTest {

	/**
	 * things to test
	 * 1. terrible stress test since this solve is O(N^4)
	 * 2.  
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
	 * private List<MazeNode> comparePath(List<MazeNode> minPath,
			List<MazeNode> plankPath) {
	 */

	/**
	 * private boolean canBeComplete(Coordinate entrance, Coordinate exit, Maze maze) {
	 */
	
	/**
	 * 	private List<MazeNode> breadthFirstSearch(Maze maze, Coordinate entrance,
			Coordinate exit){
	 */
	
	/**
	 * 	private List<MazeNode> createMinPath(HashMap<MazeNode, MazeNode> prevMap, 
			MazeNode node) {
	 */
	
	/**
	 * 	private void addAdjacentPlanks(Maze maze, Queue<MazeNode> queue,
			HashMap<MazeNode, MazeNode> prevMap, MazeNode node) {
	 */
	
	/**
	 * private boolean canPlacePlank(Maze maze, Coordinate toVisitCoord, MazeDirection direction){
	 */
}
