package oop.focus.diary.controller;

import oop.focus.common.Controller;
import org.joda.time.LocalTime;

/**
 * This interface manages a new window, which can be opened by timer to set the starter value of itself.
 */
public interface InsertTimeTimerController extends Controller {
    /**
     * The method sets the starter value of timer.
     * @param value the duration of timer, set by the user
     */
    void setNewValue(LocalTime value);
}
