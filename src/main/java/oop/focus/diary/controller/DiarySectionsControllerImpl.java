package oop.focus.diary.controller;

import javafx.scene.Node;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.db.DataSource;
import oop.focus.diary.model.DailyMoodManagerImpl;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.ToDoListManagerImpl;
import oop.focus.diary.view.BaseDiary;
import oop.focus.diary.view.StopwatchView;
import oop.focus.diary.view.TimerView;
import oop.focus.homepage.model.EventManagerImpl;

public class DiarySectionsControllerImpl implements DiarySectionsController {
    private final DataSource dataSource;
    private final EventManagerImpl eventManager;
    private final TotalTimeControllerImpl totalTimeController;
    private Node diary;
    private Node stopwatch;
    private Node timer;
    private final CalendarMonthController calendarMonthController;
    public DiarySectionsControllerImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.eventManager = new EventManagerImpl(dataSource);
        this.calendarMonthController = new CalendarMonthControllerImpl(CalendarType.DIARY, this.dataSource);
        this.totalTimeController = new TotalTimeControllerImpl(eventManager);
        this.diary = new BaseDiary(new ToDoListControllerImpl(new ToDoListManagerImpl(dataSource)),
                new DiaryPagesImpl(new DiaryDao()), new DailyMoodControllerImpl(new DailyMoodManagerImpl(dataSource))).
                getRoot();
        this.stopwatch = new StopwatchView(totalTimeController,
                new CounterControllerImpl(eventManager, false)).getRoot();
        this.timer = new TimerView(totalTimeController, new CounterControllerImpl(eventManager, true)).getRoot();
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
    public Node getMoodCalendar() {
        return this.calendarMonthController.getView().getRoot();
    }
    public CalendarMonthController getCalendarMonthController() {
        return this.calendarMonthController;
    }
}
