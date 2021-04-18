package oop.focus.diary.model;

/**
 * CounterFactory defines methods to create different {@link TimeScrolling}. Depending on
 * parameters passed to this, it is a timer or a stopwatch.
 */
public interface CounterFactory {
    /**
     * Creates a timer. Values of this counter decrease every second.
     * @return  timer
     */
    TimeScrolling createTimer();

    /**
     * Creates a stopwatch. Value of this counter increase every second.
     * @return  stopwatch
     */
    TimeScrolling createStopwatch();
}
