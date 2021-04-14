package oop.focus.calendar.week.controller;

import oop.focus.calendar.controller.CalendarMonthController;
import oop.focus.calendar.week.view.NewEventWeekViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.TimeProperty;
import oop.focus.homepage.model.TimePropertyImpl;

public class NewEventControllerImpl implements NewEventController {

    private final DataSource dsi;
    private final WeekController weekController;
    private final CalendarMonthController monthController;
    private final EventManager eventManager;
    private final TimeProperty timeProperty;
    private final NewEventWeekViewImpl view;

    public NewEventControllerImpl(final DataSource dsi, final WeekController weekController, final CalendarMonthController monthController) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(this.dsi);
        this.timeProperty = new TimePropertyImpl();
        this.monthController = monthController;
        this.weekController = weekController;

        this.view = new NewEventWeekViewImpl(this, this.weekController, this.monthController);
    }

    public final void addNewEvent(final Event event) {
        try{
            this.eventManager.addEvent(event);
        } catch (IllegalStateException e){
            throw new IllegalStateException();
        }
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final View getView() {
        return this.view;
    }

    public boolean isCompatible(Event eventToSave) {
        return this.eventManager.getAnswer(eventToSave);
    }

    public final boolean itIsValid(final Event event) {
        return this.timeProperty.getValidity(event);
    }

}
