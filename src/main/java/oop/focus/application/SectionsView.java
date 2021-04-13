package oop.focus.application;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import oop.focus.common.Controller;
import oop.focus.common.View;

public class SectionsView implements View {
    private final Pane pane;
    public SectionsView() {
        this.pane = new Pane();
    }
    public final void setPane(final Controller controller) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(controller.getView().getRoot());
    }
    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
