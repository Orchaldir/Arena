package arena.ai.pathfinding.cost;

import arena.util.map.Neighbor;

public interface CostCalculator<T> {

	double calculate(Neighbor<T> neighbor);

}
