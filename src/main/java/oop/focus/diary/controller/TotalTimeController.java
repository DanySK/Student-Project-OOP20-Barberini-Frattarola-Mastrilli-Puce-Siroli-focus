package oop.focus.diary.controller;

import oop.focus.common.Controller;

/**
 * The interface can be used to compute total time spent to do an activity and inserts the value in the
 * appropriate section of view.
 */
public interface TotalTimeController extends Controller {
    /**
     * The method computes total time spent to do the activity in input and shows this time in the appropriate
     * section of the view.
     * @param event the event of witch is computed total time
     */
    void setTotalTime(String event);
}
