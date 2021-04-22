package oop.focus.event.controller;

import javafx.collections.ObservableSet;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.event.model.Event;

import java.util.List;

public interface EventMenuController extends Controller {

    /**
     * This method is used to get the save events.
     * @return ObservableList of event.
     */
    ObservableSet<Event> getEvents();

    /**
     * This method is used to delete a specific element.
     * @param selectedItem is the event to delete.
     */
    void remove(Event selectedItem);

    /**
     * This method is used to get the dsi.
     * @return the data source.
     */
    DataSource getDsi();

    /**
     * This method is used to get the events generated from hot keys.
     * @return List that contains events generated from hot keys.
     */
    List<Event> getHotKeyEvent();

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
