package oop.focus.application;

import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.Map;


public class CommonView {
    private BorderPane pane;
    private Map<Button, BorderPane> map;
    private Integer width;
    private Integer height;
    private HBox box;
    public CommonView(final Dimension2D dim) {
        this.pane = new BorderPane();
        this.map = new HashMap<>();
        this.pane.setPrefSize(dim.getWidth(), dim.getHeight());
        this.box = new HBox();
        this.createButton();
        this.setView();
    }
    public BorderPane getPane() {
        return this.pane;
    }
    public void createButton() {
        for (var elem : Sections.values()) {
            Button button = new Button(elem.getName());
            this.box.getChildren().add(button);
            this.box.setMinWidth(0.1*width);
            this.box.setMinHeight(0.2 *height);
            button.setMinHeight(0.1 * width);
            button.setMinWidth(0.2 * width);
            BorderPane borderPane = new BorderPane();
            borderPane.getStyleClass().add(elem.getStyle());
            this.map.put(button, borderPane);
        }
    }
    public void setView() {
        box.setAlignment(Pos.CENTER);
        box.setSpacing(this.height * 0.01);
        this.pane.setTop(box);
       // BorderPane.setAlignment(iconView.getButton(), Pos.TOP_CENTER);
        if(!this.map.isEmpty()){
            for(var elem : this.map.keySet()) {
                map.get(elem).setPrefWidth(0.08 * this.width);
                map.get(elem).setPrefHeight(0.08 * this.height);
                elem.setOnMouseClicked(event -> pane.setCenter(map.get(elem)));

            }
        }
    }

}
