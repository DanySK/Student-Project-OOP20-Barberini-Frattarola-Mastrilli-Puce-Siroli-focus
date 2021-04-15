package oop.focus.diary.view;

import oop.focus.application.SectionsController;
import oop.focus.application.UpperView;
import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.controller.SectionsDiaryController;

public class ButtonsDiaryController implements Controller {
    private final UpperDiaryView upperView;
    public ButtonsDiaryController(final SectionsDiaryController controller) {
        this.upperView = new UpperDiaryView(controller);
    }

    @Override
    public final View getView() {
        return this.upperView;
    }
}
