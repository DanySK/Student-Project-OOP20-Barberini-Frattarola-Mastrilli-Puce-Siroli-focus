package oop.focus.application.controller;

import oop.focus.common.Controller;

/**
 * This interface defines methods to create new {@link Controller}. Each Controller manages a
 * section of application.
 */
public interface SectionsControllerFactory {
    /**
     * Initializes and returns the Controller of homePage's section.
     * @return  Home Page's Controller
     */
    Controller getHomePageController();

    /**
     * Initializes and returns the Controller of finance's section.
     * @return  Finance's Controller
     */
    Controller getFinanceController();

    /**
     * Initializes and returns the Controller of calendar's section.
     * @return  Calendar's Controller
     */
    Controller getCalendarController();

    /**
     * Initializes and returns the Controller of Diary's section.
     * @return  Diary's Controller
     */
    Controller getDiaryController();
}
