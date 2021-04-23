package oop.focus.calendar.month.view;

import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.db.DataSource;

/**
 * Class that implements the creation of different Calendar Month.
 */
public class CalendarMonthFactoryImpl implements CalendarMonthFactory {

    /**
     * {@inheritDoc}
     */
    public final CalendarMonthController createNormal(final DataSource dataSource) {
        return new CalendarMonthControllerImpl(CalendarType.NORMAL, dataSource);
    }

    /**
     * {@inheritDoc}
     */
    public final CalendarMonthController createHomePage(final DataSource dataSource) {
        return new CalendarMonthControllerImpl(CalendarType.HOMEPAGE, dataSource);
    }

    /**
     * {@inheritDoc}
     */
    public final CalendarMonthController createDiary(final DataSource dataSource) {
        return new CalendarMonthControllerImpl(CalendarType.DIARY, dataSource);
    }

}
