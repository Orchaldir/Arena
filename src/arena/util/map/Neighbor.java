package arena.util.map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Neighbor<T> {

	private final int index;
	private final T node;
	private final double distance;

}
