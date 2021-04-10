package oop.focus.diary.controller;

import oop.focus.application.UseCommonController;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.model.DailyMoodManagerImpl;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.ToDoListManagerImpl;
import oop.focus.homepage.model.EventManagerImpl;

public class UseDiaryControllers {
    private static final DataSourceImpl DATA_SOURCE = UseCommonController.getDataSource();
    private static final EventManagerImpl EVENT_MANAGER = UseCommonController.getEventManager();
    private static final ToDoListControllerImpl TO_DO_LIST_CONTROLLER = new ToDoListControllerImpl(new   ToDoListManagerImpl(DATA_SOURCE));
    private static final DiaryPagesImpl DIARY_PAGES = new DiaryPagesImpl(new DiaryDao());
    private static final DailyMoodControllerImpl DAILY_MOOD_CONTROLLER = new DailyMoodControllerImpl(new DailyMoodManagerImpl(DATA_SOURCE));
    private static final TotalTimeControllerImpl TOTAL_TIME_CONTROLLER = new TotalTimeControllerImpl(EVENT_MANAGER);
    private static final CounterControllerImpl COUNTER_CONTROLLER_TIMER = new CounterControllerImpl(EVENT_MANAGER, true);
    private static final CounterControllerImpl COUNTER_CONTROLLER_STOP = new CounterControllerImpl(EVENT_MANAGER, false);
    public UseDiaryControllers() {

    }
    public static ToDoListControllerImpl getToDoListController() {
        return TO_DO_LIST_CONTROLLER;
    }

    public static DiaryPagesImpl getDiaryPages() {
        return DIARY_PAGES;
    }

    public static DailyMoodControllerImpl getDailyMoodController() {
        return DAILY_MOOD_CONTROLLER;
    }

    public static TotalTimeControllerImpl getTotalTimeController() {
        return TOTAL_TIME_CONTROLLER;
    }

    public static CounterControllerImpl getCounterControllerTimer() {
        return COUNTER_CONTROLLER_TIMER;
    }

    public static CounterControllerImpl getCounterControllerStop() {
        return COUNTER_CONTROLLER_STOP;
    }
}
