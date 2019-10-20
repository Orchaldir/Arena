package arena.util.map;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ArrayMap2d<T> implements Map2d<T> {

	@Getter
	private final int width;
	@Getter
	private final int height;
	@Getter
	private final int size;

	private final T[] cells;

	public ArrayMap2d(int width, int height, T[] cells) {
		this.width = width;
		this.height = height;
		this.size = width * height;
		this.cells = cells;
	}

	@Override
	public int getIndex(int x, int y) {
		return y * width + x;
	}

	@Override
	public int getX(int index) {
		return index % width;
	}

	@Override
	public int getY(int index) {
		return index / width;
	}

	@Override
	public boolean isInsideForX(int x) {
		return x >= 0 && x < width;
	}

	@Override
	public boolean isInsideForY(int y) {
		return y >= 0 && y < height;
	}

	@Override
	public boolean isInside(int x, int y) {
		return isInsideForX(x) && isInsideForY(y);
	}

	@Override
	public boolean isInside(int index) {
		return index >=0 && index < size;
	}

	@Override
	public T getNode(int index) {
		if(isInside(index)) {
			return cells[index];
		}

		throw new OutsideMapException(index);
	}

	@Override
	public T getNode(int x, int y) {
		if(isInside(x, y)) {
			return cells[getIndex(x, y)];
		}

		throw new OutsideMapException(x, y);
	}

	@Override
	public List<Neighbor<T>> getNeighbors(int index) {
		if(!isInside(index)) {
			throw new OutsideMapException(index);
		}

		List<Neighbor<T>> neighbors = new ArrayList<>();

		final int x = getX(index);
		final int y = getY(index);

		addNeighbor(neighbors, x + 1, y);
		addNeighbor(neighbors, x, y + 1);
		addNeighbor(neighbors, x - 1, y);
		addNeighbor(neighbors, x, y - 1);

		return neighbors;
	}

	private void addNeighbor(List<Neighbor<T>> neighbors, int x, int y) {
		if(isInside(x, y)) {
			int neighborIndex = getIndex(x, y);
			neighbors.add(new Neighbor<>(neighborIndex, cells[neighborIndex], 1.0));
		}
	}
}
