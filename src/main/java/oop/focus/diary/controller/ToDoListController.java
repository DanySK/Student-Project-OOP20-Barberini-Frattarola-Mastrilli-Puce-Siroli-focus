package oop.focus.diary.controller;

import javafx.collections.ObservableSet;
import oop.focus.diary.model.ToDoAction;

/**
 * This interface has methods to add, modify or remove a toDoAction.
 */
public interface ToDoListController extends RemoveControllers<String> {
    /**
     * Returns an {@link ObservableSet} with all toDoActions saved.
     * @return  a set of toDoActions
     */
    ObservableSet<ToDoAction> allAnnotations();

    /**
     * Creates a new ToDoAction, whose annotation is the string in input and it isn't done yet.
     * @param annotation    the annotation of toDoAction to add
     */
    void addNote(String annotation);

    /**
     * Changes the checkBox's status of the ToDoAction whose annotation is the string in input.
     * @param a the annotation of toDoAction to change
     */
    void changeCheck(String a);
}
