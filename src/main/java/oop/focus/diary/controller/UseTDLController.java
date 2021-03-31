package oop.focus.diary.controller;

public final class UseTDLController {
    private static final ToDoListControllerImpl TO_DO_LIST_CONTROLLER = new ToDoListControllerImpl();

    private UseTDLController() { }

    public static ToDoListControllerImpl getCF() {
        return  TO_DO_LIST_CONTROLLER;
    }
}
