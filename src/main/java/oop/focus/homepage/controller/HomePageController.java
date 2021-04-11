package oop.focus.homepage.controller;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;

public interface HomePageController {

    Parent getView();

    ObservableList<Event> getEvents();

    ObservableList<HotKey> getHotKey();
 
    void saveHotKey(HotKey hotKey);

    void deleteHotKey(HotKeyImpl hotKeyImpl);

    void saveEvent(Event eventImpl);

    String getClickTime(HotKey hotKey);

    boolean getActivitySelected(String hotKeyName);

    void refreshDailyEvents();

    boolean canBeAdded(Event event);

    DataSourceImpl getDsi();
}
