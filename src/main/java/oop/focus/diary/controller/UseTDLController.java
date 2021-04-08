package oop.focus.diary.controller;

/**
 * This class uses singleton's pattern and creates a ToDoList's Controller.
 */
public final class UseTDLController {
    private static final ToDoListControllerImpl TO_DO_LIST_CONTROLLER = new ToDoListControllerImpl();

    private UseTDLController() { }

    public static ToDoListControllerImpl getCF() {
        return  TO_DO_LIST_CONTROLLER;
    }
}
