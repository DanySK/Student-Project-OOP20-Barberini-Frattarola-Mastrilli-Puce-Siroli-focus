package oop.focus.week.controller;

import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.TimeProperty;
import oop.focus.homepage.model.TimePropertyImpl;
import oop.focus.week.view.WeekView;
import oop.focus.week.view.WeekViewImpl;


public class WeekControllerImpl implements WeekController {

    private final WeekView view;
    private final EventManager eventManager;
    private final TimeProperty timeProperty;
    private final DataSource dsi;

    public WeekControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(this.dsi);
        this.timeProperty = new TimePropertyImpl();
        this.view = new WeekViewImpl(this);
    }

    public final void addNewEvent(final Event event) {
        this.eventManager.addEvent(event);
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final boolean itIsValid(final Event event) {
        return this.timeProperty.getValidity(event);
    }

}
