package oop.focus.diary.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import oop.focus.common.View;
import oop.focus.diary.controller.CounterControllerImpl;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class StartStopView implements View {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    private final Label counterLabel;
    private final Button start;
    private final Button stop;
    public StartStopView(final CounterControllerImpl controller) {
        this.start = new Button("Start");
        this.stop = new Button("Stop");
        this.start.setOnMouseClicked(event -> controller.startTimer());
        this.stop.setDisable(true);
        this.start.setDisable(true);
        this.stop.setOnMouseClicked(event -> controller.stopTimer());
        this.counterLabel = new Label(LocalTime.MIDNIGHT.toString(TIME_FORMATTER));
    }
    public final void disableButton(final boolean disable) {
        List.of(this.start, this.stop).forEach(s -> s.setDisable(disable));
    }
    public final void updateValue(final LocalTime localTime) {
        Platform.runLater(() -> {
            this.counterLabel.setText(localTime.toString(TIME_FORMATTER));
        });
    }
    @Override
    public final Node getRoot() {
        this.counterLabel.setAlignment(Pos.CENTER);
        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        final HBox hBox = new HBox(this.start, this.stop);
        hBox.setPadding(new Insets(20));
        hBox.spacingProperty().bind(vbox.widthProperty().multiply(0.1));
        this.start.prefWidthProperty().bind(vbox.widthProperty().multiply(0.3));
        this.stop.prefWidthProperty().bind(vbox.widthProperty().multiply(0.3));
        this.counterLabel.prefWidthProperty().bind(vbox.widthProperty().multiply(0.5));
        this.counterLabel.prefHeightProperty().bind(vbox.heightProperty().multiply(0.4));
        hBox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(this.counterLabel, hBox);
        vbox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
        return vbox;
    }
}
