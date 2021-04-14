package oop.focus.diary.controller;

import oop.focus.common.Controller;

/**
 * The interface can be used to get all events' name saved in db, and, for each of them, is computed total time
 * spent to do the event itself.
 */
public interface TotalTimeController extends Controller {
    /**
     * The method returns a set with all events' name.
     * @return  a set with all names of events saved
     */
    //ObservableSet<String> getAllEvents();

    /**
     * The method computes total time spent to do the activity in input.
     * @param event the event of witch is computed total time
     * @return  the sum of all time spent to do the event in input
     */

/*
    /**
     * The method creates new event(witch name is the string in input).
     * @param text the name of the event to add.
     */
    //void addValue(String text);



    void setTotalTime(String event);
}
