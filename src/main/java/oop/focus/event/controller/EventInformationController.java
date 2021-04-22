package oop.focus.event.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.homepage.model.Event;

public interface EventInformationController extends Controller {

    /**
     * This method is used to get the Event.
     * @return the event.
     */
    Event getEvent();

    /**
     * This method is used to stop the repetition of an events.
     */
    void stopRepetition();

    /**
     * This method is used to get the controller of the event menu.
     * @return EventMenuController is the controller pf the event menu.
     */
    EventMenuController getMenu();

    /**
     * This method is used to get all the persons that participate to the specific event.
     * @return ObservableList of String, that are the names of the persons that participate to the event.
     */
    ObservableList<String> getPersons();
}
