package oop.focus.diary.controller;

import javafx.scene.control.CheckBox;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.model.ToDoAction;
import oop.focus.diary.view.SingleCheckBoxView;

public class SingleCheckBoxController implements Controller {
    private final SingleCheckBoxView view;
    public SingleCheckBoxController() {
        this.view = new SingleCheckBoxView();
    }
    public final CheckBox createCheckBox(final ToDoAction action) {
        return this.view.createCheckBox(action);
    }

    @Override
    public final View getView() {
        return null;
    }
}
