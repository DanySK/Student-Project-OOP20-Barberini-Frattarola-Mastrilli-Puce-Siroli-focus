package oop.focus.diary.view;

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
    private final Label label;
    private final Label totalTimeLabel;

    public TotalTimeView() {
        this.label = new Label();
        this.totalTimeLabel = new Label("Tempo totale");

    }
    public final void setLabel(final LocalTime localTime) {
        this.label.setText(localTime.toString(TIME_FORMATTER));
    }
    @Override
    public final Node getRoot() {
        final VBox vBox = new VBox(this.totalTimeLabel, this.label);
        vBox.setAlignment(Pos.CENTER);
        this.label.prefWidthProperty().bind(vBox.widthProperty().multiply(0.6));
        this.label.prefHeightProperty().bind(vBox.heightProperty().multiply(0.2));
        this.label.setAlignment(Pos.CENTER);
        this.totalTimeLabel.prefWidthProperty().bind(this.label.widthProperty());
       // this.label.prefHeightProperty().bind(this.totalTimeLabel.heightProperty().multiply(0.3));
        vBox.spacingProperty().bind(vBox.heightProperty().multiply(0.2));
        vBox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
        return vBox;
    }
}
