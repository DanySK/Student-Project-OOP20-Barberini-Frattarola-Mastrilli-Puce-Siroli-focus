package oop.focus.diary.controller;

import oop.focus.calendar.month.controller.CalendarMonthFactoryImpl;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.event.model.EventManager;
/**
 * Implementation of {@link DiarySectionsControllerFactory}.
 */
public class DiarySectionsControllerFactoryImpl implements DiarySectionsControllerFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getDiaryController(final DataSource dataSource) {
        return new BaseDiaryController(dataSource);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getMoodCalendarController(final DataSource dataSource) {
        return new CalendarMonthFactoryImpl().createDiary(dataSource);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getStopwatchController(final EventManager eventManager) {
        return new GeneralCounterControllerImpl(eventManager, false);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getTimerController(final EventManager eventManager) {
        return new GeneralCounterControllerImpl(eventManager, true);
    }
}
