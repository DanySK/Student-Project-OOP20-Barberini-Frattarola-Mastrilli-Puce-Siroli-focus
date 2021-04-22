package oop.focus.calendar.week.controller;

import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.calendarhomepage.controller.HomePageController;

public interface WeekController extends Controller {

    /**
     * This method is used to get the dsi.
     * @return the data source.
     */
    DataSource getDsi();

    /**
     * This method is used to get the home page controller.
     * @return the home page controller.
     */
    HomePageController getHomePageController();
}
