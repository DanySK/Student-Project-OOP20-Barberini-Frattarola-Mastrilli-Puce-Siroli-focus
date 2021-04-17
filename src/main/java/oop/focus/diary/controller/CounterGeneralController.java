package oop.focus.diary.controller;

import oop.focus.common.Controller;
import org.joda.time.LocalTime;

public interface CounterGeneralController extends Controller {
    /**
     * The method can be used to enable/disable start/stop button.
     * @param disable   if true the buttons are disabled, otherwise buttons are enabled
     */
    void disableButton(boolean disable);

    /**
     * The method can be used to set the name of event that the counter is computing.
     * @param event the name of event that the counter is computed
     */
    void setCounterName(String event);

    /**
     * The method sets starter value of counter, which is zero in the case of stopwatch and set by the user
     * in the case of timer.
     * @param localTime the starter value of timer.
     */
    void setStarterValue(LocalTime localTime);

    /**
     * The method disable the {@link javafx.scene.control.CheckBox} and the {@link javafx.scene.control.Button}
     * that allow the insertion of a new name of an event.
     * @param disable   the boolean is true if the components must be disabled, false otherwise.
     */
    void disableChooseEvent(boolean disable);
}
