package oop.focus.diary.controller;

import oop.focus.application.controller.SectionsController;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;

import oop.focus.diary.view.ButtonsDiaryView;

public class ButtonsDiaryController implements Controller {
    private final ButtonsDiaryView upperView;
    public ButtonsDiaryController(final SectionsController controller, final DataSource dataSource) {
        this.upperView = new ButtonsDiaryView(controller, dataSource);
    }

    @Override
    public final View getView() {
        return this.upperView;
    }
}
