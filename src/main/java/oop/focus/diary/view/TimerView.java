package oop.focus.diary.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import oop.focus.diary.controller.*;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TimerView implements Initializable {
    private static final  DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    private static final  DateTimeFormatter TIME_FORMATTER_WITHOUT_HOUR = DateTimeFormat.forPattern("mm : ss");
    private static final int MINUTES_GAP = 15;
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
    private Button timer1;

    @FXML
    private Button timer3;

    @FXML
    private Button timer2;

    @FXML
    private Button otherTime;

    private List<Button> buttonList;
    private final ControllersFactory factory;

    public TimerView() {
        this.factory = new ControllerFactoryImpl();
    }


    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {

        final CounterControllerImpl specificController = this.factory.createTimer();
        final UpdateView connection = new UpdateView(specificController, this.counterLabel);
        this.buttonList = List.of(this.timer1, this.timer2, this.timer3, this.otherTime);
        this.chooseEvent.getItems().addAll(UseTotalTimeController.getTotalTimeController().getAllEvents());
        this.nameEventLabel.setText("Inserisci evento");
        this.startButton.setText("Start");
        this.startButton.setDisable(true);
        this.stopButton.setText("Stop");
        this.stopButton.setDisable(true);
        int min = MINUTES_GAP;
        for (final var elem : this.buttonList) {
            elem.setDisable(true);
            elem.setText(LocalTime.MIDNIGHT.plusMinutes(min).toString(TIME_FORMATTER_WITHOUT_HOUR));
            min += MINUTES_GAP;
            elem.setOnMouseClicked(event -> {
                specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(elem.getText(), TIME_FORMATTER_WITHOUT_HOUR));
                this.counterLabel.setText(elem.getText());
                this.disableButton(elem);
                this.startButton.setDisable(false);
            });
            this.otherTime.setText("Scegli");

        }
        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.timeLabel.setText(UseTotalTimeController.getTotalTimeController().getTotalTime(newValue).toString(TIME_FORMATTER));
            this.buttonList.forEach(s -> s.setDisable(false));
        });
        CommonView.addListener(specificController, connection, this.startButton, this.stopButton, this.chooseEvent);
        this.stopButton.setOnMouseClicked(event -> {
            CommonView.addStopTimer( specificController, this.startButton, this.stopButton, this.timeLabel, this.chooseEvent, TIME_FORMATTER);
            this.startButton.setDisable(true);
            this.buttonList.forEach(s -> s.setDisable(false));
            this.chooseEvent.setDisable(false);
        });
    }


    private void disableButton(final Button b) {
        this.buttonList.stream().filter(a -> !a.equals(b)).forEach(s -> s.setDisable(true));
    }


}

