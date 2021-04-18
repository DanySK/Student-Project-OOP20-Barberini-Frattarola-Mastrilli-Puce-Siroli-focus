package oop.focus.diary.model;

import java.util.function.Consumer;

/**
 * Every counter is reported to a specific event : this interface can be used to create  timers/stopwatch, manage 
 * their beginning/ending and create new event when one of them ends.
 *
 */
public interface CounterManager {
    /**
     * Creates a new counter and sets the name of the event that will be created when counter ends.
     * @param event     the name of event for which the user is using the counter
     */
    void createCounter(String event);

    /**
     * Starts the counter.
     */
    void startCounter();

    /**
     * Stops the counter.
     */
    void stopCounter();

    /**
     * Adds a listener to the timer/stopwatch created. This listener advises whenever counter's value changes.
     * @param consumer  the consumer of listener
     */
    void setChangeListener(Consumer<Integer> consumer);

    /**
     * Sets starter value of counter.
     * @param value     the starter value of counter
     */
    void setStarterValue(Integer value);

    /**
     * Adds a listener to the timer/stopwatch created. This listener advises whenever counter ends.
     * @param consumer  the consumer of listener
     */
    void setFinishListener(Consumer<Integer> consumer);

    /**
     * Stops counter's alarm (if it is playing).
     */
    void stopSound();

}
