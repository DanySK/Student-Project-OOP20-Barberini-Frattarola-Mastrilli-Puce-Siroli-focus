package oop.focus.diary.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import oop.focus.common.Controller;
import oop.focus.common.View;

public class SectionsDiaryView implements View {
    private final Pane pane;
    public SectionsDiaryView() {
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
