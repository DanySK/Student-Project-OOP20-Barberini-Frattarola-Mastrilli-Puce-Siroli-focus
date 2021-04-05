package oop.focus.diary.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import oop.focus.diary.controller.ControllerFactoryImpl;
import oop.focus.diary.controller.ControllersFactory;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.UpdateView;
import oop.focus.diary.controller.UseTotalTimeController;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class StopwatchView implements  Initializable {
    private static final  DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");

    @FXML
    private Label nameEventLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private ComboBox<String> chooseEvent;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label counterLabel;

    @FXML
    private Button addNewEvent;

    public final void initialize(final URL location, final ResourceBundle resources) {
        final ControllersFactory factory = new ControllerFactoryImpl();
        final CounterControllerImpl specificController = factory.createStopwatch();
        final UpdateView connection = new UpdateView(specificController, this.counterLabel);
        this.startButton.setDisable(true);
        this.stopButton.setDisable(true);
        CommonView.setConfig(this.chooseEvent, this.nameEventLabel, this.startButton, this.stopButton, this.addNewEvent, this.addNewEvent);
        this.counterLabel.setText(LocalTime.MIDNIGHT.toString(TIME_FORMATTER));

        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.timeLabel.setText(UseTotalTimeController.getTotalTimeController().getTotalTime(newValue).toString(TIME_FORMATTER));
            specificController.setStarter(newValue, LocalTime.MIDNIGHT);
            this.startButton.setDisable(false);
            }
        );
        this.startButton.setOnMouseClicked(event -> {
            CommonView.addStartListener(specificController, connection, this.startButton, this.stopButton, this.chooseEvent);
            this.addNewEvent.setDisable(true);
        });

        this.stopButton.setOnMouseClicked(event -> {
            CommonView.addStopTimer(specificController, this.startButton, this.stopButton, this.timeLabel, this.chooseEvent, TIME_FORMATTER);
            specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(this.counterLabel.getText(), TIME_FORMATTER));
            this.chooseEvent.setDisable(false);
            this.addNewEvent.setDisable(false);
        });
    }
}
