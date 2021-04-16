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

public class TotalTimeView implements View {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    private static final double LABEL_WIDTH = 0.6;
    private static final double LABEL_HEIGHT = 0.2;
    private static final double SPACING = 0.2;
    private final Label label;
    private final Label totalTimeLabel;

    public TotalTimeView() {
        this.label = new Label();
        this.totalTimeLabel = new Label("Tempo totale");

    }
    public final void setLabel(final LocalTime localTime) {
        Platform.runLater(() -> this.label.setText(localTime.toString(TIME_FORMATTER)));
    }

    @Override
    public final Node getRoot() {
        final VBox vBox = new VBox(this.totalTimeLabel, this.label);
        vBox.setAlignment(Pos.CENTER);
        this.label.prefWidthProperty().bind(vBox.widthProperty().multiply(LABEL_WIDTH));
        this.label.prefHeightProperty().bind(vBox.heightProperty().multiply(LABEL_HEIGHT));
        this.label.setAlignment(Pos.CENTER);
        this.totalTimeLabel.setAlignment(Pos.CENTER);
        this.totalTimeLabel.prefWidthProperty().bind(this.label.widthProperty());
        vBox.spacingProperty().bind(vBox.heightProperty().multiply(SPACING));
        vBox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
        return vBox;
    }
}
