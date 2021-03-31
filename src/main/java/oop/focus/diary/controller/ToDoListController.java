package oop.focus.diary.controller;

import javafx.collections.ObservableList;
import oop.focus.diary.model.ToDoAction;

/**
 * This interface has methods to add, modify or remove a toDoAction.
 */
public interface ToDoListController {
    /**
     *  This method returns an observable list with all toDoAction saved.
     * @return  a list of toDoAction
     */
    ObservableList<ToDoAction> allAnnotations();

    /**
     * This method create a new ToDoAction, whose annotation is the string in input and it isn't done yet.
     * @param annotation    the annotation of toDoAction to add
     */
    void addNote(String annotation);

    /**
     * This method change the checkBox of the ToDoAction whose annotation is the string in input.
     * @param a the annotation of toDoAction to change
     */
    void changeCheck(String a);

    /**
     * This method deletes the toDoAction whose annotation is the string in input.
     * @param a the annotation of ToDoAction to remove
     */
    void remove(String a);
}
