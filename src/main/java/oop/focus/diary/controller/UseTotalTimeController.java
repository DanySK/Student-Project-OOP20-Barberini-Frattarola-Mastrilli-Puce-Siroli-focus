package oop.focus.diary.controller;

/**
 * This class uses Singleton's pattern and creates a Total Time Controller.
 */
public final  class UseTotalTimeController {
    private static final TotalTimeControllerImpl TOTAL_TIME_CONTROLLER = new TotalTimeControllerImpl(UseEventManager.getEventManager());
    private UseTotalTimeController() {
    }
    public static TotalTimeControllerImpl getTotalTimeController() {
        return TOTAL_TIME_CONTROLLER;
    }
}


