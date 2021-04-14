package oop.focus.calendar.week.controller;

import oop.focus.calendar.week.view.WeekView;
import oop.focus.calendar.week.view.WeekViewImpl;
import oop.focus.db.DataSource;


public class WeekControllerImpl implements WeekController {

    private final WeekView view;
    private final DataSource dsi;

    public WeekControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.view = new WeekViewImpl(this);
    }

    public final DataSource getDsi() {
        return this.dsi;
    }


    public final WeekView getView() {
        return this.view;
    }
}
