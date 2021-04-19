package oop.focus.homepage.view;

import oop.focus.homepage.model.HotKeyType;
import org.joda.time.LocalDateTime;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import oop.focus.common.Repetition;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.EventImpl;

/**
 * This class is used to model an activity hot key, wich is represented from a check box.
 */
public class ActivityHotKeyView extends Pane implements HotKeyView {

    private final HomePageController controller;
    private final CheckBox checkBox;

    public ActivityHotKeyView(final String name, final HomePageController controller) {
        this.controller = controller;
        this.checkBox = new CheckBox(name);
        this.initSelection();
        this.checkBox.setStyle("-fx-background-color:" + HotKeyType.EVENT.getColor() + ";");

        this.setAction();
        this.getChildren().add(this.checkBox);
    }

    public final CheckBox getCheckBox() {
        return this.checkBox;
    }

    private void initSelection() {
        this.checkBox.setSelected(!this.controller.getActivitySelected(this.checkBox.getText()));
    }

    public final void setAction() {
        this.checkBox.setOnAction(event -> {
            if (!this.checkBox.isSelected()) {
                this.checkBox.setSelected(true);
                this.controller.saveEvent(new EventImpl(this.checkBox.getText(), LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE));
            }
       });
    }
}
