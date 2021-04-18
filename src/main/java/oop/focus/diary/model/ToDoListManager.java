package oop.focus.diary.model;

import javafx.collections.ObservableSet;

/**
 * The interface is a manager of ToDoList's section, with methods to create or delete annotations and to set if an
 * annotation has been complete.
 *
 */
public interface ToDoListManager {
    /**
     * Add new ToDoAction to ToDoList.
     * 
     * @param tda   ToDoAction's annotation
     */
    void addAnnotation(ToDoAction tda);
    /**
     * Remove the ToDoAction in input by ToDoList.
     * 
     * @param tda   the annotation to remove
     */
    void removeAnnotation(ToDoAction tda);
    /**
     * Set as done or unset the annotation of ToDoAction in input:
     * if the toDoAction's status is set the method unsets it, so on the other side.
     *
     * @param tda   the ToDoAction whose status is changed
     */
    void changeBoxStatus(ToDoAction tda);
    /**
     * Return an {@link ObservableSet} with all toDoActions.
     * 
     * @return  a set of all ToDoActions.
     */
    ObservableSet<ToDoAction> getAnnotations();
}
