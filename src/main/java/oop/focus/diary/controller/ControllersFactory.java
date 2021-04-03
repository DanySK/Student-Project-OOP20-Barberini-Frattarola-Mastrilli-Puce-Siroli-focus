package oop.focus.diary.controller;

/**
 * The interface, through Factory's pattern, create three different Controllers witch can be used by timer or stopwatch.
 */
public interface ControllersFactory {
    /**
     * Creates Timer's Controller.
     * @return  Timer's Controller
     */
    CounterControllerImpl createTimer();

    /**
     * Creates Stopwatch's Controller.
     * @return  Stopwatch's Controller
     */
    CounterControllerImpl createStopwatch();

    /**
     * Creates a general Controller which manages event and can be used by both stopwatch and timer.
     * @return  TotalTime's Controller
     */
    TotalTimeControllerImpl createCounterController();
}
