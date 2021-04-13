package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.RemovePageView;

public class RemovePageController implements Controller {
    private final View content;
    public RemovePageController(final DiaryPages controller) {
        this.content = new RemovePageView(controller);
    }

    @Override
    public final View getView() {
        return this.content;
    }
}
