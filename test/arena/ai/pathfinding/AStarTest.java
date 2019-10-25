package arena.ai.pathfinding;

import arena.ai.pathfinding.cost.CostCalculator;
import arena.ai.pathfinding.cost.DistanceAsCostCalculator;
import arena.util.map.ArrayMap2d;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.*;

class AStarTest {

	private static final CostCalculator<Double> COST_CALCULATOR = (d) -> d.getNode();

	@Test
	public void testValidPlan() {
		int width = 6;
		int height = 5;
		Double[] values = new Double[width * height];
		Arrays.fill(values, 1.0);
		values[7] = Double.MAX_VALUE;
		values[8] = Double.MAX_VALUE;
		values[9] = Double.MAX_VALUE;
		values[10] = Double.MAX_VALUE;
		values[14] = Double.MAX_VALUE;
		ArrayMap2d<Double> map = new ArrayMap2d(width, height, values);

		AStar<Double> aStar = new AStar<>(COST_CALCULATOR);

		Path path = aStar.find(map, 3, 15);

		assertNotNull(path);
		assertTrue(path.isValid());
		assertThat(path.getIndices(), contains(3, 4, 5, 11, 17, 16, 15));
	}

	@Test
	public void testNoPlan() {
		int width = 3;
		int height = 1;
		Double[] values = new Double[width * height];
		Arrays.fill(values, 1.0);
		values[1] = Double.MAX_VALUE;
		ArrayMap2d<Double> map = new ArrayMap2d(width, height, values);

		AStar<Double> aStar = new AStar<>(COST_CALCULATOR);

		Path path = aStar.find(map, 0, 2);

		assertNotNull(path);
		assertFalse(path.isValid());
		assertThat(path.getIndices(), empty());
	}

}