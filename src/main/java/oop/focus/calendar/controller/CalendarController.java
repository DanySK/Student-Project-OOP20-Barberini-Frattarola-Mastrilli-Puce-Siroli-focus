package oop.focus.calendar.controller;

import oop.focus.calendar.persons.view.PersonsView;
import oop.focus.calendar.week.controller.NewEventController;
import oop.focus.calendar.week.view.NewEventWeekViewImpl;
import oop.focus.calendar.week.view.WeekView;
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
    * @return WeekView
    */
   WeekView getWeek();

   /**
    * Used for get the New Event Controller.
    * @return AddNewEventWeekView
    */
   NewEventController getNewEvent();

   /**
    * Used for get the Person Controller.
    * @return PersonsView
    */
   PersonsView getPerson();
}
