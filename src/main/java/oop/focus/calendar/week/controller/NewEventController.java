package oop.focus.calendar.week.controller;

import javafx.collections.ObservableList;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.common.Repetition;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;

public interface NewEventController {

    /**
     * This method is used to add an event.
     * @param event is the evnt to add.
     */
    void addNewEvent(Event event);

    /**
     * This method is used to get the dsi.
     * @return the data source.
     */
    DataSource getDsi();

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
     * This method is used to get the view.
     * @return View.
     */
    View getView();

    /**
     * This method is used to get all the saved repetition.
     * @return  ObservableList<Repetition> that represent all the saved repetition.
     */
    ObservableList<Repetition> getRep();
}
