package oop.focus.application;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import oop.focus.common.Controller;
import oop.focus.common.View;
import java.util.HashMap;
import java.util.Map;

public class UpperView implements View {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final Double BOX_HEIGHT = 0.2;
    private static final Double BUTTONS_HEIGHT = 0.6;
    private static final Double INSETS = 0.01;
    private final Pane hBox;
    private final Map<Button, Controller> map;
    private final Sections controller;
    private final SectionsController sectionsController;
    public UpperView(final SectionsController sectionsController) {
        this.map = new HashMap<>();
        this.hBox = new HBox();
        this.controller = new SectionsImpl();
        this.sectionsController = sectionsController;
        this.setButtons();
    }
    private void setButtons() {
        this.controller.getList().forEach(s -> {
            final Button b = new Button(s.getValue());
            this.hBox.getChildren().add(b);
            this.map.put(b, s.getKey());
        });
        this.hBox.getChildren().forEach(s -> HBox.setMargin(s, new Insets(SCREEN_BOUNDS.getWidth() * INSETS)));
        this.hBox.prefHeightProperty().set(SCREEN_BOUNDS.getHeight() * BOX_HEIGHT);
        this.map.keySet().forEach(s -> s.prefHeightProperty().bind(this.hBox.heightProperty().multiply(BUTTONS_HEIGHT)));
        this.map.keySet().forEach(s -> s.setPrefWidth(SCREEN_BOUNDS.getWidth() / this.map.keySet().size()));
        this.setOnPress();
    }
    private void setOnPress() {
        this.hBox.getChildren().forEach(s -> s.setOnMouseClicked(event -> this.sectionsController.update(this.map.get(s))));
    }
    @Override
    public final Node getRoot() {
        return this.hBox;
    }
}
