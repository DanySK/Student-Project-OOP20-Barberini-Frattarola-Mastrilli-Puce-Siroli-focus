package oop.focus.diary.view;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oop.focus.application.SectionsController;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.diary.controller.DiarySections;

import java.util.HashMap;
import java.util.Map;

public class UpperDiaryView implements View {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final Double INSETS = 0.02;
    private static final Double BUTTONS_WIDTH = 0.25;
    private final Pane vBox;
    private final Map<Button, Controller> map;
    private final DiarySections controller;
    private final SectionsController sectionsController;
    public UpperDiaryView(final SectionsController sectionsController, final DataSource dataSource) {
        this.map = new HashMap<>();
        this.vBox = new VBox();
        this.controller = new DiarySections(dataSource);
        this.sectionsController = sectionsController;
        this.setButtons();
    }
    private void setButtons() {
        this.controller.getList().forEach(s -> {
            final Button b = new Button(s.getValue());
            this.vBox.getChildren().add(b);
            this.map.put(b, s.getKey());
        });

        this.map.keySet().forEach(s -> s.setPrefHeight(SCREEN_BOUNDS.getHeight() / this.map.keySet().size()));
        this.map.keySet().forEach(s -> s.setPrefWidth(SCREEN_BOUNDS.getWidth() * BUTTONS_WIDTH));
        this.vBox.getChildren().forEach(s -> VBox.setMargin(s, new Insets(SCREEN_BOUNDS.getHeight() * INSETS)));
        this.setOnPress();
    }
    private void setOnPress() {
        this.vBox.getChildren().forEach(s -> s.setOnMouseClicked(event -> this.sectionsController.update(this.map.get(s))));
    }
    @Override
    public final Node getRoot() {
        return this.vBox;
    }
}
