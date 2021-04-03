package oop.focus.diary.view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import oop.focus.diary.controller.TotalTimeControllerImpl;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.UpdateView;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;

public final class CommonView {
    private CommonView() {

    }
    public static void addListener(final CounterControllerImpl specificController, final UpdateView connection,
                            final Button startButton, final Button stopButton, final ComboBox<String> box) {
        final ObservableList<LocalTime> list = specificController.getValue();
        list.addListener((ListChangeListener<LocalTime>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    connection.run();
                }
            }
        });
        startButton.setOnMouseClicked(event -> {
            specificController.startTimer();
            startButton.setDisable(true);
            stopButton.setDisable(false);
            box.setDisable(true);
        });
    }
    public static void addStopTimer(final TotalTimeControllerImpl controller, final CounterControllerImpl specificController, final Button startButton,
                             final Button stopButton, final Label timeLabel, final ComboBox<String> chooseEvent, final DateTimeFormatter timeFormatter) {
        specificController.stopTimer();
        startButton.setDisable(false);
        stopButton.setDisable(true);
        timeLabel.setText(controller.getTotalTime(chooseEvent.getItems().get(0)).toString(timeFormatter));
        specificController.setStarter(chooseEvent.getItems().get(0), LocalTime.MIDNIGHT);
    }
}
