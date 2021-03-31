package oop.focus.homepage.controller;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

public class ActivityHotKeyView extends Pane {

    private boolean state;
    private final CheckBox checkBoxState;

    public ActivityHotKeyView(final String name) {
        this.checkBoxState = new CheckBox(name);
        this.state = false;
        /*final Image image = new Image(getClass().getResourceAsStream("done-tick.png"));
        button.setGraphic(new ImageView(image));*/
        this.getChildren().addAll(this.checkBoxState);
    }

    public final void check() {
        this.state = !this.state;
    }

    public final boolean getStateView() {
        return this.state;
    }

}
