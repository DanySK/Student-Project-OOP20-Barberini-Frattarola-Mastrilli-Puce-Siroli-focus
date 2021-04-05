package oop.focus.diary.view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import oop.focus.diary.controller.ControllerFactoryImpl;
import oop.focus.diary.controller.ControllersFactory;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.UpdateView;
import oop.focus.diary.controller.UseTotalTimeController;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TimerView implements Initializable {
    private static final  DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    private static final  DateTimeFormatter TIME_FORMATTER_WITHOUT_HOUR = DateTimeFormat.forPattern("mm : ss");
    private static final String SELECT_TIME = "/layouts/diary/insertTimeWindow.fxml";
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
    @FXML
    private Button addEventButton;

    private List<Button> buttonList;
    private final CounterControllerImpl specificController;

    private void setTime() {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(SELECT_TIME));
            final Consumer<String> consumer = integer -> {
                this.specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(this.otherTime.getText(), TIME_FORMATTER));
                this.counterLabel.setText(this.otherTime.getText());
                this.startButton.setDisable(false);
            };
            final InsertTimeTimerWindow timerWindow = new InsertTimeTimerWindow(consumer);
            loader.setController(timerWindow);
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TimerView() {
        final ControllersFactory factory = new ControllerFactoryImpl();
        this.specificController = factory.createTimer();
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final UpdateView connection = new UpdateView(this.specificController, this.counterLabel);
        this.manageSound();
        this.modifyAllButtons(true);
        this.buttonList = List.of(this.timer1, this.timer2, this.timer3);
        CommonView.setConfig(this.chooseEvent, this.nameEventLabel, this.startButton, this.stopButton, this.addEventButton, this.addEventButton);
        this.otherTime.setText("Scegli");
        this.otherTime.setOnMouseClicked(event -> this.setTime());
        this.setTimeButtons();
        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.timeLabel.setText(UseTotalTimeController.getTotalTimeController().getTotalTime(newValue).toString(TIME_FORMATTER));
            this.modifyAllButtons(false);
        });
        this.startButton.setOnMouseClicked(event -> {
            CommonView.addStartListener(this.specificController, connection, this.startButton, this.stopButton, this.chooseEvent);
            this.modifyAllButtons(true);
            this.addEventButton.setDisable(true);
        });
        this.setStopButton();
    }

    private void disableButton(final Button b) {
        this.buttonList.stream().filter(a -> !a.equals(b)).forEach(s -> s.setDisable(true));
    }
    private void setStopButton() {
        this.stopButton.setOnMouseClicked(event -> {
            CommonView.addStopTimer(this.specificController, this.startButton, this.stopButton, this.timeLabel, this.chooseEvent, TIME_FORMATTER);
            this.specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(this.counterLabel.getText(), TIME_FORMATTER));
            this.modifyAllButtons(false);
            this.addEventButton.setDisable(false);
            this.chooseEvent.setDisable(false);
        });
    }
    private void modifyAllButtons(final boolean disable) {
        final List<Node> list = List.of(this.timer1, this.timer2, this.timer3, this.otherTime);
        list.forEach(s -> s.setDisable(disable));
    }
    private void manageSound() {
        this.specificController.setListener(integer -> {
            if (this.specificController.isPlaying()) {
                this.stopButton.setOnMouseClicked(event -> {
                    this.specificController.stopSound();
                    this.modifyAllButtons(false);
                    this.setStopButton();
                });
            }
        });
    }
    private void setTimeButtons() {
        int min = MINUTES_GAP;
        for (final var elem : this.buttonList) {
            elem.setDisable(true);
            elem.setText(LocalTime.MIDNIGHT.plusMinutes(min).toString(TIME_FORMATTER_WITHOUT_HOUR));
            min += MINUTES_GAP;
            elem.setOnMouseClicked(event -> {
                this.specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(elem.getText(), TIME_FORMATTER_WITHOUT_HOUR));
                this.counterLabel.setText(elem.getText());
                this.disableButton(elem);
                this.otherTime.setDisable(true);
                this.startButton.setDisable(false);
            });
        }
    }
}

