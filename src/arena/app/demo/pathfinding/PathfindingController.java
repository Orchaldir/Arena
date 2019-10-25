package arena.app.demo.pathfinding;

import arena.util.rendering.CanvasRenderer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PathfindingController {

	@FXML
	private Canvas mapCanvas;

	private CanvasRenderer canvasRenderer;

	public PathfindingController() {
		log.info("PathfindingController()");

		createWorldMap();
	}

	private void createWorldMap() {
		log.info("createWorldMap()");


		log.info("createWorldMap(): finished");
	}

	@FXML
	private void initialize() {
		log.info("initialize()");

		canvasRenderer = new CanvasRenderer(mapCanvas.getGraphicsContext2D());

		render();
	}

	private void render() {
		log.info("render()");

		canvasRenderer.setColor(Color.RED);
		canvasRenderer.renderSphere(100, 200, 50);
	}


	@FXML
	public void onMouseClicked(MouseEvent mouseEvent) {
		log.info("onMouseClicked(): x={} y={} button={}", mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton());

		render();
	}
}
