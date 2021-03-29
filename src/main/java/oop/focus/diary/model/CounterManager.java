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
     * The method add a listener to the timer/stopwatch created.
     * @param consumer  the consumer of listener to crete
     */
    void setListener(Consumer<Integer> consumer);

    /**
     * The method sets starter value of counter.
     * @param value     the starter value of counter
     */
    void setStarterValue(Integer value);

}
