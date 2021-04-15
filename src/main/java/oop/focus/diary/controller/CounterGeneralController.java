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
     *
     * @param localTime
     */
    void setStarterValue(LocalTime localTime);
}
