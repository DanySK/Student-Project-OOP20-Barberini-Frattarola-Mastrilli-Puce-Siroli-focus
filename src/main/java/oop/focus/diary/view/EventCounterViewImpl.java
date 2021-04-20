package oop.focus.diary.view;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.diary.controller.EventCounterController;

import java.util.List;

/**
 * Event Counter View represents the section of counter linked to the events. It allows the display of a list of saved
 * events and a button to add new ones. When an event is selected, the appropriate section {@link TotalTimeView}
 * shows the total time devoted to this activity. If the button of "add new event " is pressed, is opened the
 * appropriate window that allows to the user to set the time of timer.
 */
public class EventCounterViewImpl implements UpdatableView<String>, DisableComponentsView {
    private static final double COMBO_BOX_WIDTH = 0.4;
    private static final double COMBO_BOX_HEIGHT = 0.2;
    private static final double LABEL_WIDTH = 0.2;
    private static final double SPACING = 0.1;
    private final Label chooseLabel;
    private final ComboBox<String> chooseEvent;
    private final EventCounterController controller;
    private final Button addNewEvent;

    /**
     * Initializes a new event counter.
     * @param controller    the event counter controller
     */
    public EventCounterViewImpl(final EventCounterController controller) {
        this.controller = controller;
        this.chooseLabel = new Label("Scegli evento");
        this.addNewEvent = new Button("+");
        this.chooseEvent = new ComboBox<>();
        this.chooseEvent.getItems().addAll(controller.getEventsNames());
        this.addNewEvent.setOnMouseClicked(event -> {
            final Scene scene = new Scene((Parent) new NewEventNameWindowImpl(controller).getRoot());
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();
        });
        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.controller.setChosen(newValue);
            this.controller.disableButton(false);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        final HBox hBox = (HBox) new ContainerFactoryImpl().mergeHorizontally(List.of(this.chooseEvent,
                this.addNewEvent)).getRoot();
        hBox.spacingProperty().bind(hBox.heightProperty().multiply(SPACING));
        hBox.setAlignment(Pos.CENTER);
        final VBox vBox = new VBox(this.chooseLabel, hBox);
        this.chooseEvent.prefWidthProperty().bind(vBox.widthProperty().multiply(COMBO_BOX_WIDTH));
        this.chooseEvent.prefHeightProperty().bind(vBox.heightProperty().multiply(COMBO_BOX_HEIGHT));
        this.chooseLabel.prefWidthProperty().bind(this.chooseEvent.prefWidthProperty());
        this.chooseLabel.setAlignment(Pos.CENTER);
        this.addNewEvent.prefWidthProperty().bind(this.chooseEvent.prefWidthProperty().multiply(LABEL_WIDTH));
        this.addNewEvent.prefHeightProperty().bind(this.chooseEvent.heightProperty());
        vBox.spacingProperty().bind(vBox.heightProperty().multiply(SPACING));
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void disableComponent(final boolean disable) {
        this.chooseEvent.setDisable(disable);
        this.addNewEvent.setDisable(disable);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final String input) {
        this.chooseEvent.setValue(input);
    }
}
