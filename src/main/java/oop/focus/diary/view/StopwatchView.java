package oop.focus.diary.view;

import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.diary.controller.*;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StopwatchView implements  Initializable {
    private static final String BASE_DIARY = "/layouts/diary/newCounterNameWindow.fxml";
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

    @FXML
    void addNewEventName(MouseEvent event) {
        try {
            final Parent root = FXMLLoader.load(this.getClass().getResource(BASE_DIARY));
            final Scene scene = new Scene(root);
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private final ControllersFactory factory;

    public StopwatchView() {
        this.factory = new ControllerFactoryImpl(); }


    public final void initialize(final URL location, final ResourceBundle resources) {
        final CounterControllerImpl specificController = this.factory.createStopwatch();
        final UpdateView connection = new UpdateView(specificController, this.counterLabel);
        this.addNewEvent.setText("+");
        this.chooseEvent.getItems().addAll(UseTotalTimeController.getTotalTimeController().getAllEvents());
        this.nameEventLabel.setText("Inserisci evento");
        this.startButton.setText("Start");
        this.startButton.setDisable(true);
        this.stopButton.setText("Stop");
        this.stopButton.setDisable(true);
        this.counterLabel.setText(LocalTime.MIDNIGHT.toString(TIME_FORMATTER));
       UseTotalTimeController.getTotalTimeController().getAllEvents().addListener(new SetChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                chooseEvent.getItems().add(change.getElementAdded());
                System.out.println("coap");
                chooseEvent.setValue(change.getElementAdded());
            }
        });
        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.timeLabel.setText(UseTotalTimeController.getTotalTimeController().getTotalTime(newValue).toString(TIME_FORMATTER));
            specificController.setStarter(newValue, LocalTime.MIDNIGHT);
            this.startButton.setDisable(false);
            }
        );
        CommonView.addListener(specificController, connection, this.startButton, this.stopButton, this.chooseEvent);
        this.stopButton.setOnMouseClicked(event -> {
            CommonView.addStopTimer( specificController, this.startButton, this.stopButton, this.timeLabel, this.chooseEvent, TIME_FORMATTER);
            this.chooseEvent.setDisable(false);
        });

    }

}
