package oop.focus.homepage.controller;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import oop.focus.common.View;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyManager;

public interface HomePageController {

    boolean canBeAdded(Event event);

    void deleteHotKey(HotKeyImpl hotKeyImpl);

    boolean getActivitySelected(String hotKeyName);

    String getClickTime(HotKey hotKey);

    DataSourceImpl getDsi();

    ObservableList<Event> getEvents();

    ObservableList<HotKey> getHotKey();

    HotKeyManager getHotKeyManager();

    View getView();

    String getText();

    void refreshDailyEvents();

    void saveEvent(Event eventImpl);

    void saveHotKey(HotKey hotKey);

    void setText(String text);

}
