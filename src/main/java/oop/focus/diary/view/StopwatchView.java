package oop.focus.diary.view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import oop.focus.diary.controller.UpdateView;
import oop.focus.diary.controller.StopwatchControllerImpl;
import org.joda.time.LocalTime;

import java.net.URL;
import java.util.ResourceBundle;

public class StopwatchView implements Initializable {

    @FXML
    private Label nameEventLabel;


    @FXML
    private ComboBox<String> chooseEvent;

    @FXML
    private Label timeLabel;

    @FXML
    private Label counterLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final StopwatchControllerImpl controller = new StopwatchControllerImpl();
        final UpdateView connection = new UpdateView(controller, this.counterLabel);
        this.chooseEvent.getItems().addAll(controller.getAllEvents());
        this.nameEventLabel.setText("Inserisci evento");
        this.startButton.setText("Start");
        this.stopButton.setText("Stop");
        this.stopButton.setDisable(true);
        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.timeLabel.setText(controller.getTotalTime(newValue).toString());
            controller.setStarter(newValue);
            }
        );
        final ObservableList<LocalTime> list = controller.getValue();
        list.addListener((ListChangeListener<LocalTime>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    connection.run();
                }
            }
        });
        this.startButton.setOnMouseClicked(event -> {
            controller.startTimer();
            this.startButton.setDisable(true);
            this.stopButton.setDisable(false);
        });
        this.stopButton.setOnMouseClicked(event -> controller.stopTimer());
    }
}
