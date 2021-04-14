package oop.focus.diary.view;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.controller.CounterController;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.GeneralControllerCounter;
import oop.focus.diary.controller.InsertTimeTimerController;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

public class TimerButtons implements View {
    private static final int MINUTES_GAP = 15;
    private static final DateTimeFormatter TIME_FORMATTER_WITHOUT_HOUR = DateTimeFormat.forPattern("mm : ss");
    private static final int BUTTON_WITH_MINUTES = 3;
    private List<Button> list;
    private GeneralControllerCounter controller;
    public TimerButtons(GeneralControllerCounter controller) {
        this.controller = controller;
        this.list = new ArrayList<>();
        this.setTimeButtons();
    }

    private void setTimeButtons() {
        int min = MINUTES_GAP;
        for (int i = 0; i < BUTTON_WITH_MINUTES; i++) {
            //elem.setDisable(true);
            Button b = new Button();
            b.setText(LocalTime.MIDNIGHT.plusMinutes(min).toString(TIME_FORMATTER_WITHOUT_HOUR));
            list.add(b);
            min += MINUTES_GAP;
            b.setOnMouseClicked(event -> {
                this.controller.setStarterValue(LocalTime.parse(b.getText(),
                        TIME_FORMATTER_WITHOUT_HOUR));

                //this.counterLabel.setText(elem.getText());
                //this.disableButton(elem);
                //this.otherTime.setDisable(true);
                //this.startButton.setDisable(false);
            });
        }
        Button b = new Button("Scegli");
        list.add(b);
        b.setOnMouseClicked(event -> {
            final Scene scene = new Scene((Parent) new InsertTimeTimerController(controller).getView().getRoot());
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();
        });
    }
    @Override
    public Node getRoot() {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(list);
        list.forEach(s -> HBox.setHgrow(s, Priority.ALWAYS));
        return hBox;
    }
}
