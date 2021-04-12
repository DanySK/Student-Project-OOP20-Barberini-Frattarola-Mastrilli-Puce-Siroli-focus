package oop.focus.application;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import oop.focus.common.Controller;
import oop.focus.common.View;
import java.util.HashMap;
import java.util.Map;

public class UpperView implements View {
    private final HBox hBox;
    private final Map<Button, Controller> map;
    private final Sections controller;
    private final SectionsController sectionsController;
    public UpperView(final SectionsController sectionsController) {
        this.map = new HashMap<>();
        this.hBox = new HBox();
        this.controller = new Sections();
        this.sectionsController = sectionsController;
        this.setButtons();
    }
    private void setButtons() {
        this.controller.getList().forEach(s -> {
            final Button b = new Button(s.getValue());
            this.hBox.getChildren().add(b);
            this.map.put(b, s.getKey());
        });
        this.setOnPress();
    }
    private void setOnPress() {
        this.hBox.getChildren().forEach(s -> s.setOnMouseClicked(event -> sectionsController.setPane(this.map.get(s))));
    }
    @Override
    public final Node getRoot() {
        return this.hBox;
    }
}
