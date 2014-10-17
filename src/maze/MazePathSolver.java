package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The class used to find the shortest path of a maze while putting down one ladder
 * @author Ted Kim
 *
 */
public class MazePathSolver {

	/**
	 * function shortestPlankPath(mazeArr, entrance, exit)
	 * these inputs collectively tell about 
	 * the size of the pillar grid and the plank layout
	 */
	/**
	 * finds the shortest path of a maze while putting down one ladder
	 * @param maze	the maze to traverse
	 * @param entrance	the coordinate of the entrance
	 * @param exit	the coordinate of the exit
	 * @return	the ordered list representing the path
	 */
	public List<MazeNode> shortestPlankPath(Maze maze, Coordinate entrance, 
			Coordinate exit){
		ExceptionUtils.checkNulls(maze, entrance, exit);
		
		//check for invalid set size for entrance and exit //entrance and exit are tuples containing the integer coordinate
		if (!canBeComplete(entrance, exit, maze)){
			return null;
		}
		//minPath   BFS on the current mazeArr . path can be implemented as a list of integer tuples
		List<MazeNode> minPath = breadthFirstSearch(maze, entrance, exit);
		//toVisit   a queue that stores the nodes to visit
		Queue<MazeNode> toVisit = new LinkedList<MazeNode>();
		//add the start node to the toVisit queue
		toVisit.add(maze.getNode(entrance));
		//while toVisit is not empty do
		while (!toVisit.isEmpty()){
			MazeNode node = toVisit.poll();
			//node:visited   true
			node.setVisited(true);
			//for all direction not yet checked in node do
			for (MazeDirection direction : MazeDirection.values()){
				Coordinate toVisitCoordinate = node.getCoordinate().getAdjacentCoordinate(direction);
				if (canPlacePlank(maze, 
						toVisitCoordinate, 
						direction)){
					
					//mazeArrPlank   copy the mazeArr
					MazeBuilder mazeBuilder = new MazeBuilder(maze.size());
					Maze mazeArrPlank;
					mazeBuilder.copyMaze(maze);
					//mazeArrPlank   put plank down in the direction
					mazeBuilder.placeLadder(direction, node.getCoordinate());
					mazeArrPlank = mazeBuilder.toMaze();
					
					//plankPath   BFS on that copy of mazeArr
					List<MazeNode> plankPath = breadthFirstSearch(mazeArrPlank, entrance, exit);
					minPath = comparePath(minPath, plankPath);
					/**
					 * Had to remove the else statement or else the pseudocode doesn't match the loop invariant
					 * and thus is incorrect
					 * Removal of else ensures that all of the maze states are examined
					 */
					//add toVisitNode to the toVisit queue
					toVisit.add(maze.getNode(toVisitCoordinate));
				}
			}
		}

		//this is done since inserting front for ArrayList takes O(N) time for each element
		//that totals to O(N^2) for N elements
		Collections.reverse(minPath);
		//return minPath
		return minPath;
	}

	private List<MazeNode> comparePath(List<MazeNode> minPath,
			List<MazeNode> plankPath) {
		//if plankPath != null and (minPath == null or length of plankPath < length of minPath ) then
		if (plankPath != null && (minPath == null || plankPath.size() < minPath.size())){//BFS returns null if there is no path to the exit
			//minPath   plankPath
			minPath = plankPath;
		}
		return minPath;
	}

	/**
	 * checks if the entrance and exit are actually in the maze
	 * @param entrance	the coordinate of the entrance
	 * @param exit	the coordinate of the exit
	 * @param maze	the maze to check
	 * @return
	 */
	private boolean canBeComplete(Coordinate entrance, Coordinate exit, Maze maze) {
		return maze.withinBounds(entrance) && maze.withinBounds(exit);
	}

	/**
	 * does a breadth first search on the maze
	 * to find the shortest path 
	 * @param maze	the maze to traverse
	 * @param entrance	the coordinate of the entrance
	 * @param exit	the coordinate of the exit
	 * @return	the ordered list representing the path
	 */
	private List<MazeNode> breadthFirstSearch(Maze maze, Coordinate entrance,
			Coordinate exit){
		List <MazeNode> minPath = new ArrayList<MazeNode>();
		Queue <MazeNode> queue = new LinkedList<MazeNode>();
		HashMap <MazeNode, MazeNode> prevMap = new HashMap<MazeNode, MazeNode>();
		queue.add(maze.getNode(entrance));
		while (!queue.isEmpty()){
			MazeNode node = queue.poll();
			if (node.getCoordinate().equals(exit)){
				//create minPath out of prevs
				minPath = createMinPath(prevMap, node);
				return minPath;
			}
			addAdjacentPlanks(maze, queue, prevMap, node);
		}
		return null;
	}

	/**
	 * creates a minimum path out of the prevs
	 * @param prevMap	the map of prevs to check
	 * @param node	the start node to traverse from
	 */
	private List<MazeNode> createMinPath(HashMap<MazeNode, MazeNode> prevMap, 
			MazeNode node) {
		List<MazeNode> minPath = new ArrayList<MazeNode>();
		MazeNode trav = node;
		while (trav != null){
			minPath.add(trav);
			trav = prevMap.get(trav);
		}
		return minPath;
	}

	/**
	 * checks if there is a plank to traverse
	 * then adds the node that the plank is pointing to the queue
	 * @param maze	the maze to check
	 * @param queue	the queue to add to
	 * @param prevMap	a map to record the planks taken
	 * @param node	the source node to check adjacents of
	 */
	private void addAdjacentPlanks(Maze maze, Queue<MazeNode> queue,
			HashMap<MazeNode, MazeNode> prevMap, MazeNode node) {
		MazeNode toPut;
		for (MazeDirection direction: MazeDirection.values()){
			toPut = maze.getNode(node.getCoordinate().getAdjacentCoordinate(direction));
			prevMap.put(toPut, node);
			queue.add(toPut);
		}
	}
	
	/**
	 * checks to see if a plank can be placed in the specified direction
	 * @param maze	the maze to check
	 * @param toVisitCoord	the coordinate to check
	 * @param direction	the specified direction
	 * @return	true if plank can be placed, false otherwise
	 */
	private boolean canPlacePlank(Maze maze, Coordinate toVisitCoord, MazeDirection direction){
		boolean canPlace = true;//using this variable to closely resemble the pseudocode
		MazeNode toVisit = maze.getNode(toVisitCoord);
		//if the direction does not lead to node inside the mazeArr then
		//continue	
		canPlace &= maze.withinBounds(toVisitCoord);
		//if toVisitNode:visited == true then
		//continue
		canPlace &= !toVisit.isVisited();
		//if direction == false then
		canPlace &= toVisit.hasLadder(direction);
		return canPlace;
	}
}
