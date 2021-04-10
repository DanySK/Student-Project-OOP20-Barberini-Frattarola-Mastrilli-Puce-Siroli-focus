package oop.focus.diary.view;

import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oop.focus.common.View;

public class GeneralView implements View {
    private BorderPane pane;
    private VBox vBox;
    public GeneralView(Dimension2D dim) {
        this.pane = new BorderPane();
        this.vBox = new VBox();
        this.setView(dim);
    }
    private void setView(Dimension2D dim) {
        /*for(var elem : DiarySections.values()) {
            Button b = new Button(elem.getName());
            b.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pane.setCenter(elem.getView());
                }
            });

         */
            Button b = new Button("ci");

            b.prefWidthProperty().bind(this.vBox.widthProperty());
            b.prefHeightProperty().bind(this.vBox.heightProperty().multiply(0.3));
            this.vBox.getChildren().add(b);
            this.vBox.prefWidthProperty().bind(this.pane.widthProperty().multiply(0.2));
            this.vBox.prefHeightProperty().bind(this.pane.heightProperty());
            this.vBox.setPadding(new Insets(20));
            this.vBox.spacingProperty().bind(this.pane.heightProperty().multiply(0.01));

            this.pane.setLeft(this.vBox);
        }


    @Override
    public Node getRoot() {
        return this.pane;
    }
}
