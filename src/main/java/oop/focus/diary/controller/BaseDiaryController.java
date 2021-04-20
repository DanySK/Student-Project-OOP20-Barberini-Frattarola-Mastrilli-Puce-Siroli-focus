package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.diary.model.DailyMoodManagerImpl;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.ToDoListManagerImpl;
import oop.focus.diary.view.BaseDiaryView;

/**
 *  BaseDiaryController is the Controller relative to the Diary's section. It's corresponding View
 *  is composed by three under-sections : diary, ToDoList and dailyMood.
 */
public class BaseDiaryController implements Controller {
    private final View baseDiary;

    /**
     * Instantiates a new base diary controller and creates the associated view.
     *
     * @param dataSource the {@link DataSource} from which to retrieve data
     */
    public BaseDiaryController(final DataSource dataSource) {
        this.baseDiary = new BaseDiaryView(new ToDoListControllerImpl(new ToDoListManagerImpl(dataSource)),
                new DiaryPagesControllerImpl(new DiaryDao()), new DailyMoodControllerImpl(new DailyMoodManagerImpl(
                        dataSource)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.baseDiary;
    }
}
