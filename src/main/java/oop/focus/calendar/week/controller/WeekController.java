package oop.focus.calendar.week.controller;

import oop.focus.calendar.week.view.WeekView;
import oop.focus.db.DataSource;
import oop.focus.homepage.controller.HomePageController;

public interface WeekController {

    /**
     * This method is used to get the dsi.
     * @return the data source.
     */
    DataSource getDsi();

    /**
     * This method is used to get the WeekView.
     * @return WeekView that represent the week view.
     */
    WeekView getView();

    /**
     * This method is used to get the home page controller.
     * @return the home page controller.
     */
    HomePageController getHomePageController();
}
