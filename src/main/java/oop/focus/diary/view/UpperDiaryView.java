package oop.focus.diary.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import oop.focus.application.Sections;
import oop.focus.application.SectionsController;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.controller.SectionsDiaryController;

import java.util.HashMap;
import java.util.Map;

public class UpperDiaryView implements View {
    private final HBox hBox;
    private final Map<Button, Controller> map;
    private final Sections controller;
    private final SectionsDiaryController sectionsController;
    public UpperDiaryView(final SectionsDiaryController sectionsController) {
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
        //this.map.keySet().forEach(s -> HBox.setHgrow(s, Priority.ALWAYS));
        this.map.keySet().forEach(s -> s.prefWidthProperty().bind(this.hBox.widthProperty().multiply(0.3)));
        // this.map.keySet().forEach(s -> s.prefHeightProperty().bind(this.hBox.heightProperty().multiply(0.2)));
        // hBox.setVisible(true);
        this.setOnPress();
    }
    private void setOnPress() {
        this.hBox.getChildren().forEach(s -> s.setOnMouseClicked(event -> this.sectionsController.setPane(this.map.get(s))));
    }
    @Override
    public final Node getRoot() {
        return this.hBox;
    }
}
