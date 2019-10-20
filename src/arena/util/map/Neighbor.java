package arena.util.map;

public class Neighbor<T> {

	private final int index;
	private final T neighbor;
	private final double distance;

	public Neighbor(int index, T neighbor, double distance) {
		this.index = index;
		this.neighbor = neighbor;
		this.distance = distance;
	}

}
