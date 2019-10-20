package arena.util.map;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Thrown to indicate that a node outside the map was tried to be accessed.
@AllArgsConstructor
@Getter
public class OutsideMapException extends RuntimeException {

	private final int x;
	private final int y;
	private final int index;
	private final boolean usedIndex;

	public OutsideMapException(int x, int y) {
		this(x, y, -1, false);
	}

	public OutsideMapException(int index) {
		this(-1, -1, index, true);
	}
}
