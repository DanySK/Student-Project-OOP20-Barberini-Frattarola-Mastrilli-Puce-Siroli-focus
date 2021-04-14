package oop.focus.diary.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import org.joda.time.LocalTime;


/**
 * This interface has methods to start or stop a counter(which could be a timer or a stopwatch).
 */
public interface CounterController extends Controller {
    /**
     * The method return an observable list with values of counter, updated whenever one second has passed.
     * @return  a list of localTime, representing counter's values
     */
    ObservableList<LocalTime> getValue();

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
     * The method can be used to check if counter's alarm is started.
     * @return  true if alarm is playing, false otherwise
     */
    boolean isPlaying();
    void disableButton(boolean disable);

}
