package oop.focus.diary.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.converter.DateTimeStringConverter;
import oop.focus.diary.controller.FXMLPaths;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class InsertTimeTimerWindow implements Initializable {
    private static final  DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");

    @FXML
    private Pane pane;
    @FXML
    private Button save;
    @FXML
    private TextField hours;
    @FXML
    private TextField seconds;
    @FXML
    private TextField minutes;
    @FXML
    private Label separate0;
    @FXML
    private Label separate1;
    private final Consumer<String> consumer;
    private Parent root;
    public InsertTimeTimerWindow(final Consumer<String> consumer) {
        this.consumer = consumer;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.INSERT_TIMER_TIME.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final Parent getRoot() {
        return this.root;
    }

    private void setTimeFormatter(final TextField field, final String string) {
        SimpleDateFormat format = new SimpleDateFormat(string);
        try {
            field.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.separate0.setText(":");
        this.separate1.setText(":");
        this.save.setText("Salva");
        this.setTimeFormatter(this.hours, "HH");
        this.setTimeFormatter(this.minutes, "mm");
        this.setTimeFormatter(this.seconds, "ss");
        this.save.setOnMouseClicked(event -> {
            String value;
            LocalTime r = new LocalTime(Integer.parseInt(this.hours.getText()), Integer.parseInt(this.minutes.getText()),
                    Integer.parseInt(this.seconds.getText()));
            value = r.toString(TIME_FORMATTER);
            consumer.accept(value);
        });
    }
}
