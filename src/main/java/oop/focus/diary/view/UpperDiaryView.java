package oop.focus.diary.view;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oop.focus.application.controller.SectionsController;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.diary.controller.DiarySections;

import java.util.HashMap;
import java.util.Map;

public class UpperDiaryView implements View {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final Double INSETS = 0.02;
    private static final Double BUTTONS_WIDTH = 0.7;
    private static final Double VBOX_WIDTH = 0.3;
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
            b.getStyleClass().addAll("lateral-button");
            this.vBox.getChildren().add(b);
            this.map.put(b, s.getKey());
        });
        this.map.keySet().forEach(s -> s.setPrefHeight(SCREEN_BOUNDS.getHeight() / this.map.keySet().size()));
        this.vBox.setPrefWidth(SCREEN_BOUNDS.getWidth() * VBOX_WIDTH);
        this.map.keySet().forEach(s -> s.prefWidthProperty().bind(this.vBox.widthProperty().multiply(BUTTONS_WIDTH)));
        this.vBox.getChildren().forEach(s -> VBox.setMargin(s, new Insets(SCREEN_BOUNDS.getHeight() * INSETS)));
        this.setOnPress();
        this.setFirstWindow();
    }
    private void setOnPress() {
        this.vBox.getChildren().forEach(s -> s.setOnMouseClicked(event -> this.sectionsController.updateInput(this.map.get(s))));
    }
    private void setFirstWindow() {
        this.sectionsController.updateInput(this.map.get(this.map.keySet().stream().filter(s -> this.map.get(s).equals(
                this.controller.getStarterController())).findAny().get()));
    }
    @Override
    public final Node getRoot() {
        return this.vBox;
    }
}
