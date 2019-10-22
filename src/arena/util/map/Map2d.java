package arena.util.map;

public interface Map2d<T> extends Graph<T> {

	int getWidth();
	int getHeight();

	int getIndex(int x, int y);
	int getX(int index);
	int getY(int index);

	boolean isInsideForX(int x);
	boolean isInsideForY(int y);
	boolean isInside(int x, int y);
	boolean isInside(int index);

	T getNode(int x, int y);
}
