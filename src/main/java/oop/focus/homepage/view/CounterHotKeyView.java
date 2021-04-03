package oop.focus.homepage.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class CounterHotKeyView extends Pane {

    private final Button button;
    private final Label label;

    public CounterHotKeyView(final String buttonName) {
        this.label = new Label("0");
        this.button = new Button(buttonName);
        this.getChildren().addAll(this.button, this.label);
    }

    public final void setLabelText(final String count) {
        this.label.setText(count);
    }

    public final Button getButton() {
        return this.button;
    }

    public final String getLabelValue() {
        return this.label.getText();
    }

}
