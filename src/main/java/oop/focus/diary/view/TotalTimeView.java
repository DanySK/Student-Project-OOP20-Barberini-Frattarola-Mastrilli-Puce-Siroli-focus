package oop.focus.diary.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import oop.focus.common.View;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Total Time View represents the section of counter in which is shown total time spent to do an event. The View
 * is updated every time that user selects a new event from the apposite section.
 */
public class TotalTimeView implements UpdatableView<LocalTime> {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    private static final double LABEL_WIDTH = 0.5;
    private static final double LABEL_HEIGHT = 0.2;
    private static final double SPACING = 0.1;
    private final Label label;
    private final Label totalTimeLabel;

    /**
     * Instantiates a new total time View
     */
    public TotalTimeView() {
        this.label = new Label();
        this.totalTimeLabel = new Label("Tempo totale");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        final VBox vBox = new VBox(this.totalTimeLabel, this.label);
        vBox.setAlignment(Pos.CENTER);
        this.label.prefWidthProperty().bind(vBox.widthProperty().multiply(LABEL_WIDTH));
        this.label.prefHeightProperty().bind(vBox.heightProperty().multiply(LABEL_HEIGHT));
        this.label.setAlignment(Pos.CENTER);
        this.label.getStyleClass().addAll("totalTimeLabel");
        this.totalTimeLabel.setAlignment(Pos.CENTER);
        this.totalTimeLabel.prefWidthProperty().bind(this.label.widthProperty());
        vBox.spacingProperty().bind(vBox.heightProperty().multiply(SPACING));
        vBox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
        return vBox;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(LocalTime input) {
        Platform.runLater(() -> this.label.setText(input.toString(TIME_FORMATTER)));
    }
}
