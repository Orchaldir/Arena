package arena.util.map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayMap2DTest extends SharedTestData {

	@Nested
	class TestGetters {

		@Test
		public void testGetWidth() {
			assertThat(MAP.getWidth(), is(equalTo(WIDTH)));
		}

		@Test
		public void testGetHeight() {
			assertThat(MAP.getHeight(), is(equalTo(HEIGHT)));
		}

		@Test
		public void testGetSize() {
			assertThat(MAP.getSize(), is(equalTo(SIZE)));
		}

		@Test
		public void testGetIndex() {
			assertThat(MAP.getIndex(X0, Y0), is(equalTo(INDEX0)));
			assertThat(MAP.getIndex(X1, Y1), is(equalTo(INDEX1)));
			assertThat(MAP.getIndex(X2, Y2), is(equalTo(INDEX2)));
			assertThat(MAP.getIndex(X3, Y3), is(equalTo(INDEX3)));
		}

		@Test
		public void testGetX() {
			assertThat(MAP.getX(INDEX0), is(equalTo(X0)));
			assertThat(MAP.getX(INDEX1), is(equalTo(X1)));
			assertThat(MAP.getX(INDEX2), is(equalTo(X2)));
			assertThat(MAP.getX(INDEX3), is(equalTo(X3)));
		}

		@Test
		public void testGetY() {
			assertThat(MAP.getY(INDEX0), is(equalTo(Y0)));
			assertThat(MAP.getY(INDEX1), is(equalTo(Y1)));
			assertThat(MAP.getY(INDEX2), is(equalTo(Y2)));
			assertThat(MAP.getY(INDEX3), is(equalTo(Y3)));
		}
	}

	@Nested
	class TestIsInsideForX {

		@Test
		public void testInside() {
			for (int x = 0; x < WIDTH; x++) {
				assertTrue(MAP.isInsideForX(x));
			}
		}

		@Test
		public void testTooFarLeft() {
			assertFalse(MAP.isInsideForX(-1));
		}

		@Test
		public void testTooFarRight() {
			assertFalse(MAP.isInsideForX(WIDTH));
		}
	}

	@Nested
	class TestIsInsideForY {

		@Test
		public void testInside() {
			for (int y = 0; y < HEIGHT; y++) {
				assertTrue(MAP.isInsideForY(y));
			}
		}

		@Test
		public void testBelow() {
			assertFalse(MAP.isInsideForY(-1));
		}

		@Test
		public void testAbove() {
			assertFalse(MAP.isInsideForY(HEIGHT));
		}
	}

	@Nested
	class TestIsInside {

		@Test
		public void testInside() {
			for (int x = 0; x < WIDTH; x++) {
				for (int y = 0; y < HEIGHT; y++) {
					assertTrue(MAP.isInside(x, y));
				}
			}
		}

		@Test
		public void testOutsideCorners() {
			assertFalse(MAP.isInside(-1, -1));
			assertFalse(MAP.isInside(-1, HEIGHT));
			assertFalse(MAP.isInside(WIDTH, -1));
			assertFalse(MAP.isInside(WIDTH, HEIGHT));

		}

		@Test
		public void testTooFarLeft() {
			for (int y = 0; y < HEIGHT; y++) {
				assertFalse(MAP.isInside(-1, y));
			}
		}

		@Test
		public void testTooFarRight() {
			for (int y = 0; y < HEIGHT; y++) {
				assertFalse(MAP.isInside(WIDTH, y));
			}
		}

		@Test
		public void testBelowTheMap() {
			for (int x = 0; x < WIDTH; x++) {
				assertFalse(MAP.isInside(x, -1));
			}
		}

		@Test
		public void testAboveTheMape() {
			for (int x = 0; x < WIDTH; x++) {
				assertFalse(MAP.isInside(x, HEIGHT));
			}
		}
	}

	@Nested
	class TestIsInsideWithIndex {

		@Test
		public void testInside() {
			for (int index = 0; index < SIZE; index++) {
				assertTrue(MAP.isInside(index));
			}
		}

		@Test
		public void testOutside() {
			assertFalse(MAP.isInside(-1));
			assertFalse(MAP.isInside(SIZE));
		}
	}

	@Nested
	class TestGetCell {

		@Test
		public void testGetCell() {
			int i = 0;

			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					assertThat(MAP.getNode(x, y), is(equalTo(i)));
					assertThat(MAP.getNode(i), is(equalTo(i)));
					i++;
				}
			}
		}

		@Test
		public void testCoordinatesAreOutside() {
			OutsideMapException exception = assertThrows(OutsideMapException.class, () -> MAP.getNode(-11, -2));

			assertThat(exception.getX(), is(equalTo(-11)));
			assertThat(exception.getY(), is(equalTo(-2)));
			assertThat(exception.getIndex(), is(equalTo(-1)));
			assertFalse(exception.isUsedIndex());
		}

		@Test
		public void testIndexIsOutside() {
			OutsideMapException exception = assertThrows(OutsideMapException.class, () -> MAP.getNode(-3));

			assertThat(exception.getX(), is(equalTo(-1)));
			assertThat(exception.getY(), is(equalTo(-1)));
			assertThat(exception.getIndex(), is(equalTo(-3)));
			assertTrue(exception.isUsedIndex());
		}
	}

	@Nested
	class TestGetNeighbors {

		private void assertNeighbor(Neighbor<Integer> neighbor, int index, double distance) {
			assertThat(neighbor.getIndex(), is(index));
			assertThat(neighbor.getNode(), is(ARRAY[index]));
			assertThat(neighbor.getDistance(), is(closeTo(distance, 0.001)));
		}

		@Test
		public void testGetNeighbors() {
			List<Neighbor<Integer>> neighbors = MAP.getNeighbors(14);

			assertThat(neighbors, hasSize(4));
			assertNeighbor(neighbors.get(0), 15, 1.0);
			assertNeighbor(neighbors.get(1), 18, 1.0);
			assertNeighbor(neighbors.get(2), 13, 1.0);
			assertNeighbor(neighbors.get(3), 10, 1.0);
		}

		@Test
		public void testGetNeighborsAtMinX() {
			List<Neighbor<Integer>> neighbors = MAP.getNeighbors(12);

			assertThat(neighbors, hasSize(3));
			assertNeighbor(neighbors.get(0), 13, 1.0);
			assertNeighbor(neighbors.get(1), 16, 1.0);
			assertNeighbor(neighbors.get(2), 8, 1.0);
		}

		@Test
		public void testGetNeighborsAtMaxX() {
			List<Neighbor<Integer>> neighbors = MAP.getNeighbors(15);

			assertThat(neighbors, hasSize(3));
			assertNeighbor(neighbors.get(0), 19, 1.0);
			assertNeighbor(neighbors.get(1), 14, 1.0);
			assertNeighbor(neighbors.get(2), 11, 1.0);
		}

		@Test
		public void testGetNeighborsAtMinY() {
			List<Neighbor<Integer>> neighbors = MAP.getNeighbors(2);

			assertThat(neighbors, hasSize(3));
			assertNeighbor(neighbors.get(0), 3, 1.0);
			assertNeighbor(neighbors.get(1), 6, 1.0);
			assertNeighbor(neighbors.get(2), 1, 1.0);
		}

		@Test
		public void testGetNeighborsAtMaxY() {
			List<Neighbor<Integer>> neighbors = MAP.getNeighbors(18);

			assertThat(neighbors, hasSize(3));
			assertNeighbor(neighbors.get(0), 19, 1.0);
			assertNeighbor(neighbors.get(1), 17, 1.0);
			assertNeighbor(neighbors.get(2), 14, 1.0);
		}

		@Test
		public void testGetNeighborsWithIndexIsOutside() {
			OutsideMapException exception = assertThrows(OutsideMapException.class, () -> MAP.getNeighbors(-8));

			assertThat(exception.getX(), is(equalTo(-1)));
			assertThat(exception.getY(), is(equalTo(-1)));
			assertThat(exception.getIndex(), is(equalTo(-8)));
			assertTrue(exception.isUsedIndex());
		}
	}

	@Nested
	class TestGetDistance {

		@Test
		public void testNeighbors() {
			assertThat(MAP.getDistance(14, 15), is(equalTo(1.0)));
			assertThat(MAP.getDistance(14, 18), is(equalTo(1.0)));
			assertThat(MAP.getDistance(14, 13), is(equalTo(1.0)));
			assertThat(MAP.getDistance(14, 10), is(equalTo(1.0)));
		}

		@Test
		public void testDiagonals() {
			assertThat(MAP.getDistance(14, 19), is(equalTo(2.0)));
			assertThat(MAP.getDistance(14, 17), is(equalTo(2.0)));
			assertThat(MAP.getDistance(14,  9), is(equalTo(2.0)));
			assertThat(MAP.getDistance(14, 11), is(equalTo(2.0)));
		}
	}
}