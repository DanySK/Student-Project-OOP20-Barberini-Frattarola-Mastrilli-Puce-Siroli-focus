package oop.focus.diary.view;

import javafx.application.Platform;
import javafx.scene.control.Label;
import oop.focus.diary.controller.CounterControllerImpl;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class UpdateView implements Runnable {
    private final CounterControllerImpl controller;
    private final Label counter;
    public UpdateView(final CounterControllerImpl controller, final Label counter) {
        this.controller = controller;
        this.counter = counter;
    }
    @Override
    public final void run() {
        Platform.runLater(() -> {
            final DateTimeFormatter fmt = DateTimeFormat.forPattern("HH : mm : ss");
            this.counter.setText(this.controller.getValue().get(this.controller.getValue().size() - 1).toString(fmt));
        });
    }
}

