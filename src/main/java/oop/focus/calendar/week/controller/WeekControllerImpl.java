package oop.focus.calendar.week.controller;

import oop.focus.calendar.week.view.WeekView;
import oop.focus.calendar.week.view.WeekViewImpl;
import oop.focus.db.DataSource;
import oop.focus.calendarhomepage.controller.HomePageController;


public class WeekControllerImpl implements WeekController {

    private final WeekView view;
    private final DataSource dsi;
    private final HomePageController homePageController;

    public WeekControllerImpl(final DataSource dsi, final HomePageController homePageController) {
        this.dsi = dsi;
        this.view = new WeekViewImpl(this);
        this.homePageController = homePageController;
    }

    public final DataSource getDsi() {
        return this.dsi;
    }


    public final WeekView getView() {
        return this.view;
    }

    public final HomePageController getHomePageController() {
        return this.homePageController;
    }
}
