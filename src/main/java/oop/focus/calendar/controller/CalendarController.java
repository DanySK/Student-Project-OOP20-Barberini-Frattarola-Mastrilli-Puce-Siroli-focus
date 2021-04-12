package oop.focus.calendar.controller;

import oop.focus.common.Controller;
import oop.focus.statistics.controller.EventsStatistics;
import oop.focus.week.view.AddNewEventWeekView;
import oop.focus.week.view.WeekView;

public interface CalendarController extends Controller {

    /**
     * Used for get the Settings Controller.
     * @return CalendarSettingsController
     */
    CalendarSettingsController getSettingsController();

    /**
     * Used for get the Month Controller.
     * @return CalendarMonthController
     */
    CalendarMonthController getMonthController();

    /**
    * Used for get the Statistics Page Controller.
    * @return EventsStatistics
    */
   EventsStatistics getStatisticsController();

   /**
    * 
    * @return WeekView
    */
   WeekView getWeek();

   /**
    *
    * @return AddNewEventWeekView
    */
   AddNewEventWeekView getNewEvent();
}
