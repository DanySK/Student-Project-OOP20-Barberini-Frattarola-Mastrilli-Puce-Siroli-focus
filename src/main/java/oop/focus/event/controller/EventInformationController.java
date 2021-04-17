package oop.focus.event.controller;

import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.common.View;
import oop.focus.homepage.model.Event;

public interface EventInformationController {

    /**
     * This method is used to get the View that represent the event information view.
     * @return a view that represent  the event information view.
     */
    View getView();

    /**
     * This method is used to get the Event.
     * @return the event.
     */
    Event getEvent();

    /**
     * This method is used to stop the repetition of an events.
     */
    void stopRepetition();

    /**
     * This method is used to get the week controller.
     * @return WeekController that is the week controller.
     */
    WeekController getWeek();

    /**
     * This method is used to get the CalendarMonthControlle.
     * @return CalendarMonthController that is the month controller.
     */
    CalendarMonthController getMonth();
}
