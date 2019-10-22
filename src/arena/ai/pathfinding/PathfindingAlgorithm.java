package arena.ai.pathfinding;

import arena.util.map.Graph;

public interface PathfindingAlgorithm<T> {

	Path find(Graph<T> graph, int start, int end);

}
