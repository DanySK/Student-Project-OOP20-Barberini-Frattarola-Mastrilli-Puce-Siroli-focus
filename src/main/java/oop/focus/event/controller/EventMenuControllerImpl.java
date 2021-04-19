package oop.focus.event.controller;

import javafx.collections.ObservableSet;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.db.DataSource;
import oop.focus.event.view.EventMenuView;
import oop.focus.event.view.EventMenuViewImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

public class EventMenuControllerImpl implements EventMenuController {

    private final DataSource dsi;
    private final EventManager eventManager;
    private final WeekController week;
    private final CalendarMonthController month;
    private final EventMenuView view;

    public EventMenuControllerImpl(final DataSource dsi, final WeekController week, final CalendarMonthController month) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(dsi);
        this.week = week;
        this.month = month;
        this.view = new EventMenuViewImpl(this);
    }


    public final ObservableSet<Event> getEvents() {
        return this.eventManager.getAller();
    }

    @Override
    public final EventMenuView getView() {
        return this.view;
    }

    @Override
    public final void remove(final Event selectedItem) {
        this.eventManager.removeEvent(selectedItem);
    }

    @Override
    public final DataSource getDsi() {
        return this.dsi;
    }

    @Override
    public final WeekController getWeek() {
        return this.week;
    }

    @Override
    public final CalendarMonthController getMonth() {
        return this.month;
    }
}
