package arena.ai.pathfinding;

import arena.ai.pathfinding.cost.CostCalculator;
import arena.util.map.Graph;
import arena.util.map.Neighbor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@AllArgsConstructor
@Slf4j
public class AStar<T> implements PathfindingAlgorithm<T> {

	@RequiredArgsConstructor
	private class AStarNode implements Comparable<AStarNode> {

		public final int index;
		public double costSoFar =  Double.MAX_VALUE;
		public double heuristic;
		public AStarNode previous;

		@Override
		public int compareTo(AStarNode other) {
			return Double.compare(heuristic, other.heuristic);
		}
	}

	private final CostCalculator<T> costCalculator;

	@Override
	public Path find(Graph<T> graph, int start, int end) {
		log.info("Find path from {} to {}.", start, end);
		PriorityQueue<AStarNode> openNodes = new PriorityQueue<>();
		AStarNode[] list = new AStar.AStarNode[graph.getSize()];
		AStarNode endNode = new AStarNode(end);
		endNode.costSoFar = 0;

		openNodes.add(endNode);
		list[end] = endNode;

		while (!openNodes.isEmpty()) {
			AStarNode currentNode = openNodes.poll();

			if(currentNode.index == start) {
				return backtrack(currentNode);
			}

			for (Neighbor<T> neighbor : graph.getNeighbors(currentNode.index)) {
				AStarNode neighborNode = list[neighbor.getIndex()];

				if(neighborNode == null) {
					neighborNode = new AStarNode(neighbor.getIndex());
					list[neighbor.getIndex()] = neighborNode;
				}

				double costToNeighbor = costCalculator.calculate(neighbor);
				double newCost = currentNode.costSoFar + costToNeighbor;

				if(costToNeighbor < Double.MAX_VALUE && newCost < neighborNode.costSoFar) {
					neighborNode.costSoFar = newCost;
					neighborNode.heuristic = newCost + graph.getDistance(neighbor.getIndex(), start);

					neighborNode.previous = currentNode;
					openNodes.add(neighborNode);
				}
			}
		}

		log.info("Failed to find a path.");

		return Path.createInvalidPath();
	}

	private Path backtrack(AStarNode startNode) {
		ArrayList<Integer> indices = new ArrayList<>();
		AStarNode currentNode = startNode;

		while(currentNode != null) {
			indices.add(currentNode.index);
			currentNode = currentNode.previous;
		}

		log.info("Found path with {} nodes.", indices.size());

		return new Path(startNode.costSoFar, indices);
	}
}
