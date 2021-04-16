package oop.focus.diary.controller;

import oop.focus.application.controller.SectionsController;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;

import oop.focus.diary.view.UpperDiaryView;

public class ButtonsDiaryController implements Controller {
    private final UpperDiaryView upperView;
    public ButtonsDiaryController(final SectionsController controller, final DataSource dataSource) {
        this.upperView = new UpperDiaryView(controller, dataSource);
    }

    @Override
    public final View getView() {
        return this.upperView;
    }
}
