package oop.focus.homepage.controller;

import oop.focus.db.DataSourceImpl;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.*;

import java.util.List;

public class HomePageController {
    DataSource dsi;
    ManagerEvent events;
    ManagerHotKey hotKeys;

    public HomePageController(){
        this.dsi = new DataSourceImpl();
        this.events = new ManagerEventImpl(dsi);
        this.hotKeys = new ManagerHotKeyImpl(dsi, events);
    }
    public List<Event> getEvent() {
        return this.events.getAll();
    }

    public List<HotKey> getHotKey() {
        return this.hotKeys.getAll();
    }

    public final void addEvent(Event e){
        this.events.addEvent(e);
    }

}
