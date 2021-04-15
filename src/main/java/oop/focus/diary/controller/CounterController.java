package oop.focus.diary.controller;

import oop.focus.common.Controller;
import org.joda.time.LocalTime;


/**
 * This interface has methods to start or stop a counter(which could be a timer or a stopwatch).
 */
public interface CounterController extends Controller {

    /**
     * The method arranges the counter to be started, setting event's name that counter is computing and
     * the started value of counter.
     * @param event the name of event to start
     * @param localTime counter's starter value (which is chosen by user in the case of timer and set to zero
     *                  in the case of stopwatch)
     */
    void setStarter(String event, LocalTime localTime);

    /**
     * The methods stops counter's alarm if it is playing.
     */
    void stopSound();

    /**
     * The method start the counter.
     */
    void startTimer();

    /**
     * The method stop the counter.
     */
    void stopTimer();

    /**
     * The method can be used to enable/disable buttons of "start" and "stop" in the view.
     * @param disable   true if the buttons should be disabled, false otherwise
     */
    void disableButton(boolean disable);

}
