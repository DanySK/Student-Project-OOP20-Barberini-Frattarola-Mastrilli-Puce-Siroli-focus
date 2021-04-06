package oop.focus.homepage.view;

import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;
import org.joda.time.LocalDateTime;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import oop.focus.common.Repetition;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.EventImpl;

public class ActivityHotKeyView extends Pane implements HotKeyView {

    private final HomePageController controller;
    private final CheckBox checkBox;

    public ActivityHotKeyView(final String name, final HomePageController controller) {
        this.controller = controller;
        this.checkBox = new CheckBox(name);
        this.setAction();
        this.getChildren().add(this.checkBox);
    }

    public final CheckBox getCheckBox() {
        return this.checkBox;
    }

    public final void setAction() {
        this.checkBox.setOnAction(event -> {
            if (this.controller.getActivitySelected(new HotKeyImpl(this.checkBox.getText(), HotKeyType.ACTIVITY))) {
                this.checkBox.setSelected(true);
                this.controller.saveEvent(new EventImpl(this.checkBox.getText(), LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE));

            }
       });
    }
}
