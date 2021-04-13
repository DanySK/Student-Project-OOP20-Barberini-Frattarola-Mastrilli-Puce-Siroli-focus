package oop.focus.diary.view;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import oop.focus.common.View;
import oop.focus.diary.model.ToDoAction;

public class SingleCheckBoxView implements View {
    private CheckBox box;

    public SingleCheckBoxView() {
        this.box = new CheckBox();
    }
    public final CheckBox createCheckBox(final ToDoAction action) {
        this.box = new CheckBox(action.getAnnotation());
        box.setSelected(action.isDone());
        return box;
    }
    @Override
    public final Node getRoot() {
        return null;
    }
}
