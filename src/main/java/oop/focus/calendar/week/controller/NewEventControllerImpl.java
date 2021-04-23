package oop.focus.calendar.week.controller;

import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.week.view.NewEventWeekViewImpl;
import oop.focus.common.Repetition;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.event.model.Event;
import oop.focus.event.model.EventManager;
import oop.focus.event.model.EventManagerImpl;
import oop.focus.event.model.TimeProperty;
import oop.focus.event.model.TimePropertyImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NewEventControllerImpl implements NewEventController {

    private final WeekController weekController;
    private final CalendarMonthController monthController;
    private final EventManager eventManager;
    private final NewEventWeekViewImpl view;
    private final TimeProperty time;

    public NewEventControllerImpl(final DataSource dsi, final WeekController weekController, final CalendarMonthController monthController) {
        this.eventManager = new EventManagerImpl(dsi);
        this.monthController = monthController;
        this.weekController = weekController;
        this.time = new TimePropertyImpl();

        this.view = new NewEventWeekViewImpl(this);
    }

    public final void addNewEvent(final Event event) {
        this.eventManager.addEvent(event);
        this.eventManager.generateRepeatedEvents(LocalDate.now());
    }

    public final View getView() {
        return this.view;
    }

    @Override
    public final CalendarMonthController getMonth() {
        return this.monthController;
    }

    public final ObservableList<Repetition> getRep() {
        final ObservableList<Repetition> list = FXCollections.observableArrayList();
        final List<Repetition> arrayList = new ArrayList<>(Arrays.asList(Repetition.values()));
        list.addAll(arrayList);
        return list;
    }

    @Override
    public final boolean getDuration(final Event eventToSave) {
        return this.time.getMinEventTime(eventToSave);
    }

    @Override
    public final WeekController getWeek() {
        return this.weekController;
    }
}
