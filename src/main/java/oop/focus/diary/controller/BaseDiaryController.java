package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.diary.model.DailyMoodManagerImpl;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.ToDoListManagerImpl;
import oop.focus.diary.view.BaseDiary;

public class BaseDiaryController implements Controller {
    private final View baseDiary;
    public BaseDiaryController(final DataSource dataSource) {
        this.baseDiary = new BaseDiary(new ToDoListControllerImpl(new ToDoListManagerImpl(dataSource)),
                new DiaryPagesImpl(new DiaryDao()), new DailyMoodControllerImpl(new DailyMoodManagerImpl(dataSource)));
    }
    @Override
    public final View getView() {
        return this.baseDiary;
    }
}
