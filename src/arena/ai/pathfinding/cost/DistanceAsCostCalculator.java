package arena.ai.pathfinding.cost;

import arena.util.map.Neighbor;

public class DistanceAsCostCalculator<T> implements CostCalculator<T> {

	@Override
	public double calculate(Neighbor<T> neighbor) {
		return neighbor.getDistance();
	}

}
