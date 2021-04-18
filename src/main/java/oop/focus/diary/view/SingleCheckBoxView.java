package oop.focus.diary.view;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import oop.focus.common.View;
import oop.focus.diary.model.ToDoAction;

public class SingleCheckBoxView implements View {
    private CheckBox box;
    private ToDoAction action;
    public SingleCheckBoxView(final ToDoAction action ) {
        this.box = new CheckBox();
        this.action = action;
    }
    @Override
    public final Node getRoot() {
        this.box = new CheckBox(action.getAnnotation());
        box.setSelected(action.isDone());
        return this.box;
    }
}
