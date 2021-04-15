package oop.focus.diary.controller;

import javafx.scene.Node;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.diary.model.DailyMoodManagerImpl;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.ToDoListManagerImpl;
import oop.focus.diary.view.BaseDiary;
import oop.focus.diary.view.GeneralDiaryView;
import oop.focus.homepage.model.EventManagerImpl;

public class DiarySectionsControllerImpl implements DiarySectionsController {
    private final Node diary;
    private final Node stopwatch;
    private final Node timer;
    private final GeneralDiaryView content;
    private final CalendarMonthController calendarMonthController;
    public DiarySectionsControllerImpl(final DataSource dataSource) {
        this.content = new GeneralDiaryView(this);
        final EventManagerImpl eventManager = new EventManagerImpl(dataSource);
        this.calendarMonthController = new CalendarMonthControllerImpl(CalendarType.DIARY, dataSource);
        this.diary = new BaseDiary(new ToDoListControllerImpl(new ToDoListManagerImpl(dataSource)),
                new DiaryPagesImpl(new DiaryDao()), new DailyMoodControllerImpl(new DailyMoodManagerImpl(dataSource))).
                getRoot();
        this.stopwatch = new CounterGeneralControllerImpl(eventManager, false).getView().getRoot();
        this.timer = new CounterGeneralControllerImpl(eventManager, true).getView().getRoot();
    }
    @Override
    public final Node getDiary() {
        return this.diary;
    }
    @Override
    public final Node getStopwatch() {
        return this.stopwatch;
    }
    @Override
    public final Node getTimer() {
        return this.timer;
    }

    public final Node getMoodCalendar() {
        return this.calendarMonthController.getView().getRoot();
    }
    public final CalendarMonthController getCalendarMonthController() {
        return this.calendarMonthController;
    }

    @Override
    public final View getView() {
        return this.content;
    }
}
