package oop.focus.application.view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import oop.focus.common.View;
import oop.focus.diary.view.UpdatableView;

/**
 * The class manages all sections' view of application. It creates a {@link BorderPane}, sets its dimension
 * and has a method to update its content.
 */
public class SectionsView implements UpdatableView<View> {
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
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final View input) {
        this.pane.getChildren().clear();
        this.pane.setCenter(input.getRoot());
    }
}
