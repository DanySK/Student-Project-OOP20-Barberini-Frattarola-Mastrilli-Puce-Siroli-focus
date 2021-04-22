package oop.focus.calendarhomepage.view;

import oop.focus.calendarhomepage.model.HotKeyType;
import org.joda.time.LocalDateTime;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import oop.focus.common.Repetition;
import oop.focus.calendarhomepage.controller.HomePageController;
import oop.focus.event.model.EventImpl;

/**
 * This class is used to model an activity hot key, which is represented from a check box.
 */
public class ActivityHotKeyView extends Pane implements HotKeyView {

    private final HomePageController controller;
    private final CheckBox checkBox;

    public ActivityHotKeyView(final String name, final HomePageController controller) {
        this.controller = controller;
        this.checkBox = new CheckBox(name);
        this.checkBox.setStyle("-fx-background-color:" + HotKeyType.ACTIVITY.getColor() + ";");

        this.setAction();
        this.initSelection();
        this.getChildren().add(this.checkBox);
    }

    private void initSelection() {
        this.checkBox.setSelected(!this.controller.getActivitySelected(this.checkBox.getText()));
    }

    public final void setAction() {
        this.checkBox.selectedProperty().addListener(event -> {
            if (this.checkBox.isSelected()) {
                this.controller.saveEvent(new EventImpl(this.checkBox.getText(), LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE));
                this.checkBox.setDisable(true);
            }
        });
    }
}
