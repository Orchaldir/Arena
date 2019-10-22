package arena.ai.pathfinding;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Path {

	private final double totalCost;
	private final List<Integer> indices;

	public Path(double totalCost, List<Integer> indices) {
		this.totalCost = totalCost;
		this.indices = new ArrayList<>(indices);
	}

	public static Path createInvalidPath() {
		return new Path(Double.MAX_VALUE, Collections.emptyList());
	}

	public boolean isValid() {
		return !indices.isEmpty();
	}

}
