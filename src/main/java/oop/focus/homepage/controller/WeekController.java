package oop.focus.homepage.controller;

import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.view.WeekView;

public class WeekController {

    private final WeekView view;
    private final EventManager eventManager;
    private final DataSourceImpl dsi;

    public WeekController(final DataSourceImpl dsi) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(this.dsi);
        this.view = new WeekView(this);
    }

    public final void addNewEvent(final Event event) {
        this.eventManager.addEvent(event);
    }
}
