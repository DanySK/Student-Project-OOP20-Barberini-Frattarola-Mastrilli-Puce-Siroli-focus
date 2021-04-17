package oop.focus.diary.controller;

import oop.focus.common.Controller;
/**
 * This interface defines methods to create new {@link Controller}. Each Controller manages a
 * under-section of diary.
 */
public interface DiarySectionsControllerFactory {
    /**
     * Initializes and returns the Controller of diary's section.
     * @return  Diary's Controller
     */
    Controller getDiaryController();
    /**
     * Initializes and returns the Controller of mood calendar's section.
     * @return  Mood Calendar's Controller
     */
    Controller getMoodCalendarController();
    /**
     * Initializes and returns the Controller of stopwatch's section.
     * @return  Stopwatch's Controller
     */
    Controller getStopwatchController();
    /**
     * Initializes and returns the Controller of Timer's section.
     * @return  Timer's Controller
     */
    Controller getTimerController();
}
