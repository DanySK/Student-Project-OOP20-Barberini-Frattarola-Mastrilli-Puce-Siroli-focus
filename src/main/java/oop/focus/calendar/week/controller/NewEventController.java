package oop.focus.calendar.week.controller;

import javafx.collections.ObservableList;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.common.Controller;
import oop.focus.common.Repetition;
import oop.focus.event.model.Event;

public interface NewEventController extends Controller {

    /**
     * This method is used to add an event.
     * @param event is the evnt to add.
     */
    void addNewEvent(Event event);

    /**
     * This method is used to get the month controller.
     * @return CalendarMonthController
     */
    CalendarMonthController getMonth();

    /**
     * This method is used to get the week controller.
     * @return WeekController.
     */
    WeekController getWeek();

    /**
     * This method is used to get all the saved repetition.
     * @return  ObservableList that represent all the saved repetition.
     */
    ObservableList<Repetition> getRep();
}
