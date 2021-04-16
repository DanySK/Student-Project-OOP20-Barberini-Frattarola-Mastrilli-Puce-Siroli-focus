package oop.focus.event.controller;

import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;

public interface EventMenuController {

    ObservableList<Event> getEvents();

    View getView();

    void remove(Event selectedItem);

    DataSource getDsi();

}
