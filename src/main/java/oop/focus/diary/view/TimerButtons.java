package oop.focus.diary.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.GeneralCounterController;
import oop.focus.diary.controller.InsertTimeTimerControllerImpl;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimerButtons implements View {
    private static final Double BUTTONS_WIDTH = 0.2;
    private static final Integer INSETS = 20;
    private static final int MINUTES_GAP = 15;
    private static final DateTimeFormatter TIME_FORMATTER_WITHOUT_HOUR = DateTimeFormat.forPattern("mm : ss");
    private static final int BUTTON_WITH_MINUTES = 3;
    private final List<Button> list;
    private final GeneralCounterController controller;
    public TimerButtons(final GeneralCounterController controller) {
        this.controller = controller;
        this.list = new ArrayList<>();
        this.setTimeButtons();
    }

    private void setTimeButtons() {
        int min = MINUTES_GAP;
        for (int i = 0; i < BUTTON_WITH_MINUTES; i++) {
            final Button b = new Button();
            b.setText(LocalTime.MIDNIGHT.plusMinutes(min).toString(TIME_FORMATTER_WITHOUT_HOUR));
            this.list.add(b);
            min += MINUTES_GAP;
            b.setOnMouseClicked(event -> this.controller.setStarterValue(LocalTime.parse(b.getText(),
                    TIME_FORMATTER_WITHOUT_HOUR)));
        }
        final Button b = new Button("Scegli");
        this.list.add(b);
        b.setOnMouseClicked(event -> {
            final Scene scene = new Scene((Parent) new InsertTimeTimerControllerImpl(this.controller).getView().getRoot());
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();
        });
    }
    public void disableButtons(final boolean disable) {
        this.list.forEach(s -> s.setDisable(disable));
    }
    @Override
    public final Node getRoot() {
        final HBox hBox = new HBox();
        hBox.getChildren().addAll(this.list);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(INSETS);
        hBox.setPadding(new Insets(INSETS));
        this.list.forEach(s -> HBox.setHgrow(s, Priority.ALWAYS));
        this.list.forEach(s -> s.prefWidthProperty().bind(hBox.widthProperty().multiply(BUTTONS_WIDTH)));
        return hBox;
    }
}
