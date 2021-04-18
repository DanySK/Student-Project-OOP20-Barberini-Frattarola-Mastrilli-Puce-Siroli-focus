package oop.focus.homepage.controller;

import javafx.scene.Node;
import oop.focus.common.Controller;
import oop.focus.common.View;

public interface GeneralHomePageController extends Controller {

    /**
     * This method is used to get the calendar HomePage.
     * @return a Node that represent the root of the calendar homepage.
     */
    Node getCalendarHomePage();

    /**
     * This method is used to get the finance HomePage.
     * @return a Node that represent the root of the finance homepage.
     */
    Node getFinanaceHomePage();

    /**
     * This method is used to get the view of the homepage.
     * @return View that represent the view of the homepage.
     */
    View getView();

    /**
     * This method is used to get the calendarHomePage controller.
     * @return HomePageController that is the controller of the homepage.
     */
     HomePageController getHomePageController();
}
