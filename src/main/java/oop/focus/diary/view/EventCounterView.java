package oop.focus.diary.view;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.EventCounterControllerImpl;
import java.util.List;

public class EventCounterView implements View {
    private static final double COMBO_BOX_WIDTH = 0.6;
    private static final double COMBO_BOX_HEIGHT = 0.2;
    private static final double LABEL_WIDTH = 0.3;
    private static final double SPACING = 0.2;
    private final Label chooseLabel;
    private final ComboBox<String> chooseEvent;
    private final EventCounterControllerImpl controller;
    private final Button addNewEvent;
    public EventCounterView(final EventCounterControllerImpl controller) {
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
    public void disableChooseEvent(final boolean disable) {
        this.chooseEvent.setDisable(disable);
        this.addNewEvent.setDisable(disable);
    }
    public final void setNewValue(final String name) {
        this.chooseEvent.setValue(name);
    }
    @Override
    public final Node getRoot() {
        final VBox vBox = new VBox(this.chooseLabel, this.chooseEvent);
        vBox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
        this.chooseEvent.prefWidthProperty().bind(vBox.widthProperty().multiply(COMBO_BOX_WIDTH));
        this.chooseEvent.prefHeightProperty().bind(vBox.heightProperty().multiply(COMBO_BOX_HEIGHT));
        this.chooseLabel.prefWidthProperty().bind(this.chooseEvent.prefWidthProperty());
        this.chooseLabel.setAlignment(Pos.CENTER);
        this.addNewEvent.prefWidthProperty().bind(this.chooseEvent.prefWidthProperty().multiply(LABEL_WIDTH));
        vBox.spacingProperty().bind(vBox.heightProperty().multiply(SPACING));
        vBox.setAlignment(Pos.CENTER);
        return new CreateBoxFactoryImpl().createHBox(List.of(vBox, this.addNewEvent)).getRoot();
    }
}
