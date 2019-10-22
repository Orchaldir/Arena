package arena.util.map;

import java.util.List;

public interface Graph<T> {

	int getSize();

	T getNode(int index);

	List<Neighbor<T>> getNeighbors(int index);

	double getDistance(int from, int to);

}
