package oop.focus.calendar.controller;

import oop.focus.common.Controller;

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

}
