package oop.focus.application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import oop.focus.common.Controller;
import oop.focus.common.View;

public class SectionsView implements View {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final double PANE_HEIGHT = 0.6;
    private final BorderPane pane;
    public SectionsView() {
        this.pane = new BorderPane();
        this.pane.prefHeightProperty().set(SCREEN_BOUNDS.getHeight() * PANE_HEIGHT);
    }
    public final void setPane(final Controller controller) {
        this.pane.getChildren().clear();
        this.pane.setCenter(controller.getView().getRoot());
    }
    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
