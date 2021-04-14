package oop.focus.calendar.week.controller;

import oop.focus.calendar.week.view.WeekView;
import oop.focus.db.DataSource;

public interface WeekController {

    DataSource getDsi();

    WeekView getView();
}
