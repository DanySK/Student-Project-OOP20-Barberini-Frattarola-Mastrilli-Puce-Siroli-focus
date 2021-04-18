package oop.focus.diary.model;

import java.util.function.Consumer;

/**
 * The interface TimeScrolling can be used to manage the scroll of time of a counter
 * It has methods to start or stop the counter and to set/get the value of the counter itself.
 */
public interface TimeScrolling {
    /**
     * @return the value of counter 
     */
    int getCounter();
    /**
     * Start counter.
     */
    void startCounter();
    /**
     * Stop the counter. 
     */
    void stopCounter();
    /**
     * Sets the initial value of the counter.
     * @param starterCounter    the initial value to assign to the counter
     */
    void setStarterValue(int starterCounter);
    /**
     * The method add a listener to the class.
     * The listener warns that a counter is over.
     * @param consumer  the consumer to add
     */
    void addFinishListener(Consumer<Integer> consumer);
    /**
     * The method add a listener to the class.
     * The listener warns that the counter's value is changed.
     * @param consumer  the consumer to add
     */
    void addChangeListener(Consumer<Integer> consumer);


}
