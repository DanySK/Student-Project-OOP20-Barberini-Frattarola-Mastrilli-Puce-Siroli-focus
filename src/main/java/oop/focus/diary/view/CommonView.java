package oop.focus.diary.view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import oop.focus.diary.controller.TotalTimeControllerImpl;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.UpdateView;

import oop.focus.diary.controller.UseTotalTimeController;
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
    public static void addStopTimer( final CounterControllerImpl specificController, final Button startButton,
                                    final Button stopButton, final Label timeLabel, final ComboBox<String> chooseEvent, final DateTimeFormatter timeFormatter) {
        specificController.stopTimer();
        startButton.setDisable(false);
        stopButton.setDisable(true);
        timeLabel.setText(UseTotalTimeController.getTotalTimeController().getTotalTime(chooseEvent.getValue()).toString(timeFormatter));
        System.out.println(UseTotalTimeController.getTotalTimeController().getTotalTime(chooseEvent.getValue()));
        specificController.setStarter(chooseEvent.getValue(), LocalTime.MIDNIGHT);
    }
}
