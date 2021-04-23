package oop.focus.calendar.month.view;

import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.db.DataSource;

/**
 * interface that implements the creation of different Calendar Month.
 */
public interface CalendarMonthFactory {

    /**
     * Used for create a Normal Month Calendar.
     * @param dataSource : the {@link DataSource} from which to retrieve data
     * @return CalendarMonthController
     */
    CalendarMonthController createNormal(DataSource dataSource);

    /**
     * Used for create a HomePage Month Calendar.
     * @param dataSource : the {@link DataSource} from which to retrieve data
     * @return CalendarMonthController
     */
    CalendarMonthController createHomePage(DataSource dataSource);

    /**
     * Used for create a Diary Month Calendar.
     * @param dataSource : the {@link DataSource} from which to retrieve data
     * @return CalendarMonthController
     */
    CalendarMonthController createDiary(DataSource dataSource);
}
