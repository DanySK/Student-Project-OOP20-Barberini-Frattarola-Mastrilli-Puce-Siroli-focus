package oop.focus.diary.view;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.EventCounterController;

public class EventCounterView implements View {
    private final Label chooseLabel;
    private final ComboBox<String> chooseEvent;
    private final EventCounterController controller;
    private final Button addNewEvent;
    //private VBox vBox;
    public EventCounterView(final EventCounterController controller) {
        this.controller = controller;
        this.chooseLabel = new Label("Scegli evento");
        this.addNewEvent = new Button("+");
        this.chooseEvent = new ComboBox<>();
        this.chooseEvent.getItems().addAll(controller.getEventsNames());
        this.addNewEvent.setOnMouseClicked(event -> {
            final Scene scene = new Scene((Parent) new NewEventNameImpl(controller).getRoot());
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();
        });
        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.controller.setChosen(newValue);
            this.controller.disableButton(false);
        });
    }

    public final void setNewValue(final String name) {
        this.chooseEvent.setValue(name);
    }
    @Override
    public final Node getRoot() {
        final VBox vBox = new VBox(this.chooseLabel, this.chooseEvent);
        vBox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
        this.chooseEvent.prefWidthProperty().bind(vBox.widthProperty().multiply(0.6));
        this.chooseEvent.prefHeightProperty().bind(vBox.heightProperty().multiply(0.2));
        this.chooseLabel.prefWidthProperty().bind(this.chooseEvent.prefWidthProperty());
        this.chooseLabel.setAlignment(Pos.CENTER);
        this.addNewEvent.prefWidthProperty().bind(this.chooseEvent.prefWidthProperty().multiply(0.3));
        //vBox.setPadding(new Insets(20));
        vBox.spacingProperty().bind(vBox.heightProperty().multiply(0.2));
        vBox.setAlignment(Pos.CENTER);

        final HBox hBox = new HBox(vBox, this.addNewEvent);
       // hBox.setPadding(new Insets(20));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().forEach(s -> HBox.setHgrow(s, Priority.ALWAYS));


        return hBox;
    }
}
