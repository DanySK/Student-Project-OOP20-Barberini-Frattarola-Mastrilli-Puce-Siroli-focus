package oop.focus.week.controller;

import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;

public interface WeekController {

    void addNewEvent(Event event);

    DataSource getDsi();

    boolean itIsValid(Event event);
}
