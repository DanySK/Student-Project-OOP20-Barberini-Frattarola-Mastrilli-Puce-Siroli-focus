package oop.focus.diary.controller;

import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
/**
 * Implementation of {@link DiarySectionsControllerFactory}.
 */
public class DiarySectionsControllerFactoryImpl implements DiarySectionsControllerFactory {
    private final DataSource dataSource;
    private final EventManager eventManager;
    private final Controller baseDiary;

    /**
     * Instantiates a new diary sections controller factory.
     *
     * @param dataSource    dataSource the {@link DataSource} from which to retrieve data
     */
    public DiarySectionsControllerFactoryImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.eventManager = new EventManagerImpl(dataSource);
        this.baseDiary = new BaseDiaryController(this.dataSource);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getDiaryController() {
        return this.baseDiary;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getMoodCalendarController() {
        return new CalendarMonthControllerImpl(CalendarType.DIARY, this.dataSource);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getStopwatchController() {
        return new GeneralCounterControllerImpl(this.eventManager, false);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getTimerController() {
        return new GeneralCounterControllerImpl(this.eventManager, true);
    }
}
