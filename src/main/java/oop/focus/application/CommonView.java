package oop.focus.application;

import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.Map;


public class CommonView {
    private final BorderPane pane;
    //private final Map<Button, BorderPane> map;
    private final HBox box;
    public CommonView(final Dimension2D dim) {
        this.pane = new BorderPane();
        //this.map = new HashMap<>();
        this.pane.setPrefSize(dim.getWidth(), dim.getHeight());
        this.box = new HBox();
        this.box.prefWidthProperty().bind(this.pane.widthProperty());
        this.box.prefHeightProperty().bind(this.pane.heightProperty().multiply(0.15));
        this.box.spacingProperty().bind(this.pane.widthProperty().multiply(0.07));
        this.box.paddingProperty().setValue(new Insets(20));
        this.box.setAlignment(Pos.CENTER);
        this.createButton();
       // this.setView();
    }
    public BorderPane getPane() {
        return this.pane;
    }
    public void createButton() {
        for (var elem : Sections.values()) {
            Button button = new Button(elem.getName());
            this.box.getChildren().add(button);
            button.prefHeightProperty().bind(this.box.heightProperty());
            button.prefWidthProperty().bind(this.box.widthProperty().multiply(0.2));
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CommonView.this.pane.setCenter(elem.getView());
                }
            });
            BorderPane borderPane = new BorderPane();
            borderPane.getStyleClass().add(elem.getStyle());
        }
        this.pane.setTop(this.box);

    }
    /*public void setView() {
        this.pane.setTop(box);
        if(!this.map.isEmpty()){
            for(var elem : this.map.keySet()) {
                map.get(elem).setPrefWidth(0.08 * pane.getWidth());
                map.get(elem).setPrefHeight(0.08 * pane.getHeight());
                elem.setOnMouseClicked(event -> pane.setCenter(map.get(elem)));

            }
        }
    }

     */

}
