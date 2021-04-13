package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.RemoveTDLView;

public class RemoveTDLController implements Controller {
    private final View content;
    public RemoveTDLController(final ToDoListController controller) {
        this.content = new RemoveTDLView(controller);
    }
    @Override
    public final View getView() {
        return this.content;
    }
}
