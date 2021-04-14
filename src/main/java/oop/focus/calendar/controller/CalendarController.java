package oop.focus.calendar.controller;

import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.settings.controller.CalendarSettingsController;
import oop.focus.calendar.week.controller.NewEventController;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.common.Controller;
import oop.focus.statistics.controller.EventsStatistics;

/**
 * Interface that models a Calendar Controller.
 * Is used for get the controller of all the component of the calendar page.
 */
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
    * Used for get the Statistics Controller.
    * @return EventsStatistics
    */
   EventsStatistics getStatisticsController();

   /**
    * Used for get the Week Controller.
    * @return WeekController
    */
   WeekController getWeekController();

   /**
    * Used for get the New Event Controller.
    * @return NewEventController
    */
   NewEventController getNewEventController();

   /**
    * Used for get the Person Controller.
    * @return PersonsController
    */
   PersonsController getPersonController();
}
