package arena.util.rendering;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class CanvasRenderer implements Renderer {

	private final GraphicsContext graphicsContext;

	@Override
	public void clear(double x, double y, double width, double height) {
		graphicsContext.clearRect(x, y, width, height);
	}

	@Override
	public void setScale(double scale) {
		graphicsContext.scale(scale, scale);
	}

	@Override
	public void setColor(Color color) {
		graphicsContext.setFill(color);
	}

	@Override
	public void renderRectangle(double x, double y, double width, double height) {
		graphicsContext.fillRect(x, y, width, height);
	}

	@Override
	public void renderSphere(double x, double y, double radius) {
		double size = radius * 2.0;
		graphicsContext.fillOval(x - radius, y - radius, size, size);
	}
}
