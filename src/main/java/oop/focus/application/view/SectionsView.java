package oop.focus.application.view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import oop.focus.common.View;

/**
 * The class manages all sections' view of application. It creates a {@link BorderPane}, sets its dimension
 * and has a method to update its content.
 */
public class SectionsView implements View {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final double PANE_HEIGHT = 0.95;
    private static final double PANE_WIDTH = 0.8;
    private final BorderPane pane;
    public SectionsView() {
        this.pane = new BorderPane();
        this.pane.prefHeightProperty().set(SCREEN_BOUNDS.getHeight() * PANE_HEIGHT);
        this.pane.prefWidthProperty().set(SCREEN_BOUNDS.getWidth() * PANE_WIDTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRoot() {
        return this.pane;
    }

    /**
     * Sets the new content of {@link BorderPane}, replacing the previous one with the new View associated with
     * the Controller in input.
     * @param controller    the controller whose view has to be shown.
     */
    public final void update(final View controller) {
        this.pane.getChildren().clear();
        this.pane.setCenter(controller.getRoot());
    }
}
