package oop.focus.calendar.week.controller;

import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.week.view.NewEventWeekViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import org.joda.time.LocalDate;

public class NewEventControllerImpl implements NewEventController {

    private final DataSource dsi;
    private final WeekController weekController;
    private final CalendarMonthController monthController;
    private final EventManager eventManager;
    private final NewEventWeekViewImpl view;

    public NewEventControllerImpl(final DataSource dsi, final WeekController weekController, final CalendarMonthController monthController) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(this.dsi);
        this.monthController = monthController;
        this.weekController = weekController;

        this.view = new NewEventWeekViewImpl(this);
    }

    public final void addNewEvent(final Event event) {
        try {
            this.eventManager.addEvent(event);
            this.eventManager.generateRepeatedEvents(LocalDate.now());
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        }
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final View getView() {
        return this.view;
    }

    @Override
    public final CalendarMonthController getMonth() {
        return this.monthController;
    }

    @Override
    public final WeekController getWeek() {
        return this.weekController;
    }
}
