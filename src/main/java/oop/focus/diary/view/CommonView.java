package oop.focus.diary.view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.UpdateView;

import oop.focus.diary.controller.UseTotalTimeController;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * This class has method that can be used by two different classes: StopwatchView and TimerView.
 */
public final class CommonView {
    private static final String BASE_DIARY = "/layouts/diary/newCounterNameWindow.fxml";
    private CommonView() {

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
                                    final Button stopButton, final Label timeLabel, final ComboBox<String> chooseEvent, final DateTimeFormatter timeFormatter) {
        specificController.stopTimer();
        startButton.setDisable(false);
        stopButton.setDisable(true);
        timeLabel.setText(UseTotalTimeController.getTotalTimeController().getTotalTime(chooseEvent.getValue()).toString(timeFormatter));

    }
    public static void openWindow(final String path) {
        try {
            final Parent root = FXMLLoader.load(CommonView.class.getResource(path));
            final Scene scene = new Scene(root);
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setConfig(final ComboBox<String> chooseEvent, final Label nameEventLabel, final Button startButton,
                          final Button stopButton, final Button eventButton, final Button addNewEvent) {
        chooseEvent.getItems().addAll(UseTotalTimeController.getTotalTimeController().getAllEvents());
        nameEventLabel.setText("Inserisci evento");
        startButton.setText("Start");
        stopButton.setText("Stop");
        eventButton.setText("+");
        addNewEvent.setOnMouseClicked(event -> CommonView.openWindow(BASE_DIARY));
        UseTotalTimeController.getTotalTimeController().getAllEvents().addListener((SetChangeListener<String>) change -> {
            if (change.wasAdded()) {
                chooseEvent.getItems().add(change.getElementAdded());
                chooseEvent.setValue(change.getElementAdded());
            } else if (change.wasRemoved()) {
                chooseEvent.getItems().remove(change.getElementRemoved());
            }
        });
    }
}
