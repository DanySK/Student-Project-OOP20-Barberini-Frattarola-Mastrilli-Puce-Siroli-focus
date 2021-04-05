package oop.focus.diary.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DateTimeStringConverter;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InsertTimeTimerWindow  {
    private static final  DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    @FXML
    private final Button save;
    @FXML
    private final TextField hours;
    @FXML
    private final TextField seconds;
    @FXML
    private final TextField minutes;

    private final List<Consumer<String>> onFinishListener;
    public InsertTimeTimerWindow(final Consumer<String> consumer) {
        this.save = new Button();
        this.hours = new TextField();
        this.minutes = new TextField();
        this.seconds = new TextField();
        this.initialize();
        this.onFinishListener = new ArrayList<>();
        this.onFinishListener.add(consumer);

    }
    public final void initialize() {
        this.save.setText("Save");
        this.setTimeFormatter(this.hours, "HH");
        this.setTimeFormatter(this.minutes, "mm");
        this.setTimeFormatter(this.seconds, "ss");
        this.save.setOnMouseClicked(event -> {
            String value;
            LocalTime r = new LocalTime(Integer.parseInt(this.hours.getText()), Integer.parseInt(this.minutes.getText()),
                    Integer.parseInt(this.seconds.getText()));
            value = r.toString(TIME_FORMATTER);
            this.onFinishListener.forEach(s -> s.accept(value));
        });
    }
    private void setTimeFormatter(final TextField field, final String string) {
        SimpleDateFormat format = new SimpleDateFormat(string);
        try {
            field.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
