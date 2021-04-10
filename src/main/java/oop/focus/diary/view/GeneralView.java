package oop.focus.diary.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oop.focus.common.View;

public class GeneralView implements View {
    private static final double BUTTON_HEIGHT = 0.3;
    private static final double VBOX_WIDTH = 0.2;
    private static final double VBOX_SPACING = 0.01;
    private static final double INSETS = 20;
    private final BorderPane pane;
    private final VBox vBox;

    public GeneralView() {
        this.pane = new BorderPane();
        this.vBox = new VBox();
        this.setView();
    }
    private void setView() {
        for (final var elem : DiarySections.values()) {
            final Button b = new Button(elem.getName());
            b.setOnMouseClicked(event -> pane.setCenter(elem.getView()));
            b.prefWidthProperty().bind(this.vBox.widthProperty());
            b.prefHeightProperty().bind(this.vBox.heightProperty().multiply(BUTTON_HEIGHT));
            this.vBox.getChildren().add(b);
            this.vBox.prefWidthProperty().bind(this.pane.widthProperty().multiply(VBOX_WIDTH));
            this.vBox.prefHeightProperty().bind(this.pane.heightProperty());
            this.vBox.setPadding(new Insets(INSETS));
            this.vBox.spacingProperty().bind(this.pane.heightProperty().multiply(VBOX_SPACING));
            this.pane.setLeft(this.vBox);
        }
        }


    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
