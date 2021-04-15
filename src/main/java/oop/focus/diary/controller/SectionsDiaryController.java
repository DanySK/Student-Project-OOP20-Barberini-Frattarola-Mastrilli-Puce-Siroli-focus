package oop.focus.diary.controller;

import oop.focus.application.SectionsView;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.SectionsDiary;
import oop.focus.diary.view.SectionsDiaryView;

public class SectionsDiaryController implements Controller{
    private final SectionsDiaryView view;
    public SectionsDiaryController() {
        this.view = new SectionsDiaryView();
    }
    public final void setPane(final Controller controller) {
        this.view.setPane(controller);
    }
    @Override
    public final View getView() {
        return this.view;
    }
}
