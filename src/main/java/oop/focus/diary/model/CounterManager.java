package oop.focus.diary.model;

import java.util.function.Consumer;

/**
 * Every counter is reported to a specific event : this interface can be used to create  timers/stopwatch, manage 
 * their start/stop and create new event when one of them ends. 
 *
 */
public interface CounterManager {
    /**
     * The method create a new counter and sets the name of the event that will be created when counter ends.
     * @param event     the name of event for which the user is using the counter
     */
    void createCounter(String event);

    /**
     * The method starts the counter.
     */
    void startCounter();

    /**
     * The method stops the counter.
     */
    void stopCounter();

    /**
     * The method add a listener to the timer/stopwatch created. This listener advise whenever counter's value changes.
     * @param consumer  the consumer of listener
     */
    void setChangeListener(Consumer<Integer> consumer);

    /**
     * The method sets starter value of counter.
     * @param value     the starter value of counter
     */
    void setStarterValue(Integer value);

    /**
     * The method add a listener to the timer/stopwatch created. This listener advise whenever counter ends.
     * @param consumer  the consumer of listener
     */
    void setFinishListener(Consumer<Integer> consumer);

    /**
     * The method is called when a counter ends and create new event.
     */
     void createEvent();

    /**
     * The method stops counter's alarm (if it is playing).
     */
    void stopSound();

    /**
     * The method can be used to know if an alarm's counter is playing or not.
     * @return  true if alarm is playing, false otherwise.
     */
    boolean isPlaying();

}
