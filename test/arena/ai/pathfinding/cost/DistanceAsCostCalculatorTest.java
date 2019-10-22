package arena.ai.pathfinding.cost;

import arena.util.map.Neighbor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class DistanceAsCostCalculatorTest {

	public static final double DISTANCE = 345.6;
	private Neighbor<Integer> neighbor;
	private DistanceAsCostCalculator<Integer> calculator;

	@BeforeEach
	public void setUp() {
		neighbor = mock(Neighbor.class);
		calculator = new DistanceAsCostCalculator<>();
	}

	@Test
	public void test() {
		when(neighbor.getDistance()).thenReturn(DISTANCE);

		assertThat(calculator.calculate(neighbor), is(equalTo(DISTANCE)));

		verify(neighbor, only()).getDistance();
		verifyNoMoreInteractions(neighbor);
	}
}