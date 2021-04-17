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
import oop.focus.diary.controller.CounterController;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.CounterGeneralController;
import oop.focus.diary.controller.CounterGeneralControllerImpl;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class StartStopView implements View {
    private static final Integer INSETS = 20;
    private static final Double SPACING = 0.1;
    private static final Double BUTTONS_WIDTH = 0.3;
    private static final Double LABEL_WIDTH = 0.5;
    private static final Double LABEL_HEIGHT = 0.4;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    private final Label counterLabel;
    private final Button start;
    private final Button stop;
    public StartStopView(final CounterController controller, final CounterGeneralController controllerCounter) {
        this.start = new Button("Start");
        this.stop = new Button("Stop");
        this.start.setOnMouseClicked(event -> {
            controller.startTimer();
            this.start.setDisable(true);
            this.stop.setDisable(false);
        });
        this.stop.setDisable(true);
        this.start.setDisable(true);
        this.counterLabel = new Label(LocalTime.MIDNIGHT.toString(TIME_FORMATTER));
        this.stop.setOnMouseClicked(event -> {
            controller.stopSound();
            controller.stopTimer();
            controllerCounter.setStarterValue(LocalTime.parse(this.counterLabel.getText(), TIME_FORMATTER));
            System.out.println(this.counterLabel.getText());
            this.start.setDisable(false);
            this.stop.setDisable(true);
        });

    }
    public final void disableButton(final boolean disable) {
        List.of(this.start, this.stop).forEach(s -> s.setDisable(disable));
    }
    public final void updateValue(final LocalTime localTime) {
        Platform.runLater(() -> this.counterLabel.setText(localTime.toString(TIME_FORMATTER)));
    }
    @Override
    public final Node getRoot() {
        this.counterLabel.setAlignment(Pos.CENTER);
        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        final HBox hBox = new HBox(this.start, this.stop);
        hBox.setPadding(new Insets(INSETS));
        hBox.spacingProperty().bind(vbox.widthProperty().multiply(SPACING));
        this.start.prefWidthProperty().bind(vbox.widthProperty().multiply(BUTTONS_WIDTH));
        this.stop.prefWidthProperty().bind(vbox.widthProperty().multiply(BUTTONS_WIDTH));
        this.counterLabel.prefWidthProperty().bind(vbox.widthProperty().multiply(LABEL_WIDTH));
        this.counterLabel.prefHeightProperty().bind(vbox.heightProperty().multiply(LABEL_HEIGHT));
        hBox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(this.counterLabel, hBox);
        vbox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
        return vbox;
    }
}
