package oop.focus.diary.model;

import java.util.function.Consumer;

/**
 * The interface TimeScrolling can be used to manages the scroll of time of a counter
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
     * Return a boolean that is true if counter is over, false otherwise.
     * @return  if counter it's over
     */
    boolean end();
    /**
     * The method add a listener to the class.
     * @param consumer  the consumer to add
     */
    void addListener(Consumer<Integer> consumer);

}
