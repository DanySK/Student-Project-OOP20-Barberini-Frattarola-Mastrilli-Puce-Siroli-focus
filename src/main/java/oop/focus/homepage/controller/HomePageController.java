package oop.focus.homepage.controller;

import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;
import oop.focus.db.DataSource;

import java.util.List;

public class HomePageController {
    private final DataSource dsi;
    private final EventManager events;
    private final HotKeyManager hotKeys;

    public HomePageController() {
        this.dsi = new DataSourceImpl();
        this.events = new EventManagerImpl(dsi);
        this.hotKeys = new  HotKeyManagerImpl(dsi, events);
    }

    public final List<Event> getEvent() {
        return this.events.getAll();
    }

    public final List<HotKey> getHotKey() {
        return this.hotKeys.getAll();
    }

    public final void addEvent(final Event e) {
        this.events.addEvent(e);
    }

}
