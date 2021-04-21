package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.event.model.EventManager;

/**
 * This interface defines methods to create new {@link Controller}. Each Controller manages a
 * under-section of diary.
 */
public interface DiarySectionsControllerFactory {
    /**
     * Initializes and returns the Controller of diary's section.
     * @param dataSource    the {@link DataSource} from which to retrieve data
     * @return  Diary's Controller
     */
    Controller getDiaryController(DataSource dataSource);
    /**
     * Initializes and returns the Controller of mood calendar's section.
     * @param dataSource    the {@link DataSource} from which to retrieve data
     * @return  Mood Calendar's Controller
     */
    Controller getMoodCalendarController(DataSource dataSource);
    /**
     * Initializes and returns the Controller of stopwatch's section.
     * @param eventManager  the event manager
     * @return  Stopwatch's Controller
     */
    Controller getStopwatchController(EventManager eventManager);
    /**
     * Initializes and returns the Controller of Timer's section.
     * @param eventManager  the event manager
     * @return  Timer's Controller
     */
    Controller getTimerController(EventManager eventManager);
}
