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
public class TempleOfDoomMazePath {

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

		//check for invalid set size for entrance and exit //entrance and exit are tuples containing the integer coordinate
		if (!areWithinBounds(entrance, exit, maze)){
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
				if (checkPlankPlacement(maze, 
						toVisitCoordinate, 
						direction)){
					
					//mazeArrPlank   copy the mazeArr
					MazeBuilder mazeBuilder = new MazeBuilder(maze.size());
					Maze mazeArrPlank;
					mazeBuilder.copyExistingMaze(maze);
					//mazeArrPlank   put plank down in the direction
					mazeBuilder.putDownLadder(direction, node.getCoordinate());
					mazeArrPlank = mazeBuilder.toMaze();
					
					//plankPath   BFS on that copy of mazeArr
					List<MazeNode> plankPath = breadthFirstSearch(mazeArrPlank, entrance, exit);
					//if plankPath != null and (minPath == null or length of plankPath < length of minPath ) then
					if (plankPath != null && (minPath == null || plankPath.size() < minPath.size())){//BFS returns null if there is no path to the exit
						//minPath   plankPath
						minPath = plankPath;
					}
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

	/**
	 * checks if the entrance and exit are actually in the maze
	 * @param entrance	the coordinate of the entrance
	 * @param exit	the coordinate of the exit
	 * @param maze	the maze to check
	 * @return
	 */
	private boolean areWithinBounds(Coordinate entrance, Coordinate exit, Maze maze) {
		return maze.isWithinBounds(entrance) && maze.isWithinBounds(exit);
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
				createMinPath(minPath, prevMap, node);
				return minPath;
			}
			addAdjacentPlanksToQueue(maze, queue, prevMap, node);
		}
		return null;
	}

	/**
	 * creates 
	 * @param minPath
	 * @param prevMap
	 * @param node
	 */
	private void createMinPath(List<MazeNode> minPath,
			HashMap<MazeNode, MazeNode> prevMap, MazeNode node) {
		MazeNode trav = node;
		while (trav != null){
			minPath.add(trav);
			trav = prevMap.get(trav);
		}
	}


	private void addAdjacentPlanksToQueue(Maze maze, Queue<MazeNode> queue,
			HashMap<MazeNode, MazeNode> prevMap, MazeNode node) {
		MazeNode toPut;
		if (node.hasUp()){
			toPut = maze.getNode(node.getCoordinate().getUpCoordinate());
			prevMap.put(toPut, node);
			queue.add(toPut);
		}
		if (node.hasDown()){
			toPut = maze.getNode(node.getCoordinate().getDownCoordinate());
			prevMap.put(toPut, node);
			queue.add(toPut);
		}
		if (node.hasLeft()){
			toPut = maze.getNode(node.getCoordinate().getLeftCoordinate());
			prevMap.put(toPut, node);
			queue.add(toPut);
		}
		if (node.hasRight()){
			toPut = maze.getNode(node.getCoordinate().getRightCoordinate());
			prevMap.put(toPut, node);
			queue.add(toPut);
		}
	}
	
	private boolean checkPlankPlacement(Maze maze, Coordinate toVisitCoord, MazeDirection direction){
		boolean canPlace = true;//using this variable to closely resemble the pseudocode
		MazeNode toVisit = maze.getNode(toVisitCoord);
		//if the direction does not lead to node inside the mazeArr then
		//continue	
		canPlace &= maze.isWithinBounds(toVisitCoord);
		//if toVisitNode:visited == true then
		//continue
		canPlace &= !toVisit.isVisited();
		//if direction == false then
		canPlace &= toVisit.hasLadder(direction);
		return canPlace;
	}
}
