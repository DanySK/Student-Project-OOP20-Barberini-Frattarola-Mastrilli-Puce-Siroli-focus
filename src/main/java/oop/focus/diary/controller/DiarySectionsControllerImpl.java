package oop.focus.diary.controller;

import javafx.scene.Node;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.model.DailyMoodManagerImpl;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.ToDoListManagerImpl;
import oop.focus.diary.view.BaseDiary;
import oop.focus.diary.view.StopwatchView;
import oop.focus.diary.view.TimerView;
import oop.focus.homepage.model.EventManagerImpl;

public class DiarySectionsControllerImpl implements DiarySectionsController {
    private final DataSourceImpl dataSource;
    private final EventManagerImpl eventManager;
    private final TotalTimeControllerImpl totalTimeController;
    public DiarySectionsControllerImpl(final DataSourceImpl dataSource, final EventManagerImpl eventManager) {
        this.dataSource = dataSource;
        this.eventManager = eventManager;
        this.totalTimeController = new TotalTimeControllerImpl(eventManager);
    }
    @Override
    public final Node getDiary() {
        return new BaseDiary(new ToDoListControllerImpl(new ToDoListManagerImpl(dataSource)),
                new DiaryPagesImpl(new DiaryDao()), new DailyMoodControllerImpl(new DailyMoodManagerImpl(dataSource))).
                getRoot();
    }
    @Override
    public final Node getStopwatch() {
        return new StopwatchView(totalTimeController,
                new CounterControllerImpl(eventManager, false)).getRoot();
    }
    @Override
    public final Node getTimer() {
        return new TimerView(totalTimeController, new CounterControllerImpl(eventManager, true)).getRoot();
    }
}
