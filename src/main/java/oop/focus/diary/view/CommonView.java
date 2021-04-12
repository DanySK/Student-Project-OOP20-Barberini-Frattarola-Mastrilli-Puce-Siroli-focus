package oop.focus.diary.view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import oop.focus.diary.controller.CounterControllerImpl;

import oop.focus.diary.controller.TotalTimeControllerImpl;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

/**
 * This class has method that can be used by two different classes: StopwatchView and TimerView.
 */
public final class CommonView {
    private static final double LABEL_WIDTH_PERCENTAGE = 0.25;

    private CommonView() {

    }
    public static void setDimLabel(final List<Label> node, final Pane pane, final double labelHeightPercentage) {
        node.forEach(s -> {
            s.prefWidthProperty().bind(pane.widthProperty().multiply(LABEL_WIDTH_PERCENTAGE));
            s.prefHeightProperty().bind(pane.heightProperty().multiply(labelHeightPercentage));
            s.setAlignment(Pos.CENTER);
        });
    }
    public static void setDimButton(final List<Button> button, final Pane pane, final double buttonWidthPercentage,
                              final double labelHeightPercentage) {
        button.forEach(s -> {
            s.prefWidthProperty().bind(pane.widthProperty().multiply(buttonWidthPercentage));
            s.prefHeightProperty().bind(pane.heightProperty().multiply(labelHeightPercentage));
        });
    }
    public static void setGrid(final GridPane grid, final Pane pane, final double hGapPercentage, final double vGapPercentage,
                        final Label counterLabel, final Label nameEventLabel) {
        grid.hgapProperty().bind(pane.widthProperty().multiply(hGapPercentage));
        grid.vgapProperty().bind(pane.heightProperty().multiply(vGapPercentage));
        pane.getChildren().add(grid);
        GridPane.setHalignment(counterLabel, HPos.CENTER);
        GridPane.setHalignment(nameEventLabel, HPos.CENTER);
    }
    private static void updateCounter(final CounterControllerImpl specificController, final UpdateView connection) {
        final ObservableList<LocalTime> list = specificController.getValue();
        list.addListener((ListChangeListener<LocalTime>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    connection.run();
                }
            }
        });
    }
    public static void addStartListener(final CounterControllerImpl specificController, final UpdateView connection,
                                         final Button startButton, final Button stopButton, final ComboBox<String> box) {
        updateCounter(specificController, connection);
        specificController.startTimer();
        startButton.setDisable(true);
        stopButton.setDisable(false);
        box.setDisable(true);
    }
    public static void addStopTimer(final CounterControllerImpl specificController, final Button startButton,
                                    final Button stopButton, final Label timeLabel, final ComboBox<String> chooseEvent,
                                    final DateTimeFormatter timeFormatter, final TotalTimeControllerImpl controller) {
        specificController.stopTimer();
        startButton.setDisable(false);
        stopButton.setDisable(true);
        timeLabel.setText(controller.getTotalTime(chooseEvent.getValue()).toString(timeFormatter));

    }
    private static void openWindow(final TotalTimeControllerImpl controller) {
        final Scene scene = new Scene((Parent) new NewEventNameImpl(controller).getRoot());
        final Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }
    public static void setConfig(final ComboBox<String> chooseEvent, final Label nameEventLabel, final Button startButton,
                                 final Button stopButton, final Button eventButton, final Button addNewEvent,
                                 final TotalTimeControllerImpl controller) {
        chooseEvent.getItems().addAll(controller.getAllEvents());
        nameEventLabel.setAlignment(Pos.CENTER);
        nameEventLabel.setText("Inserisci evento");
        nameEventLabel.setWrapText(true);
        startButton.setText("Start");
        startButton.setWrapText(true);
        stopButton.setText("Stop");
        stopButton.setWrapText(true);
        eventButton.setText("+");
        eventButton.setWrapText(true);
        addNewEvent.setOnMouseClicked(event -> CommonView.openWindow(controller));
        controller.getAllEvents().addListener((SetChangeListener<String>) change -> {
            if (change.wasAdded()) {
                chooseEvent.getItems().add(change.getElementAdded());
                chooseEvent.setValue(change.getElementAdded());
            } else if (change.wasRemoved()) {
                chooseEvent.getItems().remove(change.getElementRemoved());
            }
        });
    }
}
