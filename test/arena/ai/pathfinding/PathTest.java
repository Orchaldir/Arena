package arena.ai.pathfinding;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class PathTest {

	public static final double TOTAL_COST = 123.4;
	private static final List<Integer> INDICES = List.of(2, 4, 8);

	@Test
	public void testValidPath() {
		Path path = new Path(TOTAL_COST, INDICES);

		assertTrue(path.isValid());
		assertThat(path.getTotalCost(), is(TOTAL_COST));
		assertThat(path.getIndices(), equalTo(INDICES));
	}

	@Test
	public void testInvalidPath() {
		Path path = Path.createInvalidPath();

		assertFalse(path.isValid());
		assertThat(path.getTotalCost(), is(Double.MAX_VALUE));
		assertThat(path.getIndices(), empty());
	}

}