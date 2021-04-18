package oop.focus.event.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.common.View;
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

    public final ObservableList<Event> getEvents() {
        final ObservableList<Event> list = FXCollections.observableArrayList();
        final List<Event> listDaily = this.eventManager.getDailyEvents().stream().collect(Collectors.toList());
        final List<Event> listEvent = this.eventManager.getEvents().stream().collect(Collectors.toList());
        listDaily.forEach(e -> listEvent.add(e));
        listEvent.stream().sorted(Comparator.comparing(Event :: getName)).collect(Collectors.toList());
        listEvent.stream().forEach(p -> list.add(p));
        return list;
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
