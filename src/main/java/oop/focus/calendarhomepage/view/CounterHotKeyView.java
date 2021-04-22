package oop.focus.calendarhomepage.view;

import oop.focus.calendarhomepage.model.HotKeyImpl;
import oop.focus.calendarhomepage.model.HotKeyType;
import org.joda.time.LocalDateTime;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import oop.focus.common.Repetition;
import oop.focus.calendarhomepage.controller.HomePageController;
import oop.focus.event.model.EventImpl;

/**
 * This class is used to model an event hot key, wich is represented from a button, and a label.
 */
public class CounterHotKeyView extends HBox implements HotKeyView {

    private final HomePageController controller;
    private final Button button;
    private final Label label;

    public CounterHotKeyView(final String buttonName, final HomePageController controller) {
        this.controller = controller;
        this.label = new Label();
        this.button = new Button(buttonName);
        this.setAction();
        this.label.setText(this.controller.getClickTime(new HotKeyImpl(this.button.getText(), HotKeyType.COUNTER)));
        this.getChildren().addAll(this.button, this.label);
        this.setStyle("-fx-background-color:" + HotKeyType.COUNTER.getColor() + ";");
    }

    public final String getLabelValue() {
        return this.label.getText();
    }

    public final void setAction() {
        this.button.setOnAction(event -> {

            this.controller.saveEvent(new EventImpl(this.button.getText(), LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE));
            final int count = Integer.parseInt(this.label.getText()) + 1;
            this.label.setText(String.valueOf(count));
        });
    }


}
