package oop.focus.diary.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class UpdateView implements Runnable {
    private final StopwatchControllerImpl controller;
    private final Label counter;
    public UpdateView(final StopwatchControllerImpl controller, final Label counter) {
        this.controller = controller;
        this.counter = counter;
    }
    @Override
    public final void run() {
        Platform.runLater(() -> {
            final DateTimeFormatter fmt = DateTimeFormat.forPattern("HH : mm : ss");
            this.counter.setText(this.controller.getCounter().toString(fmt));
        });
    }
}

