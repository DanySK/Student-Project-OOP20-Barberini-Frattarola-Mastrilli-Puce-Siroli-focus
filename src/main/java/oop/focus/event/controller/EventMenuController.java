package oop.focus.event.controller;

import javafx.collections.ObservableSet;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.db.DataSource;
import oop.focus.event.view.EventMenuView;
import oop.focus.homepage.model.Event;

public interface EventMenuController {

    /**
     * This method is used to get the save events.
     * @return ObservableList of event.
     */
    ObservableSet<Event> getEvents();

    /**
     * This method is used to get the view that represent the view of the event menu.
     * @return a view that represent the view of the event menu.
     */
    EventMenuView getView();

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
