package oop.focus.diary.model;

import java.util.List;

/**
 * The interface is a manager of ToDoList's section, with method to create or delete annotations and to set if an annotation has been complete.
 *
 */
public interface ToDoListManager {
    /**
     * Add new ToDoList's annotation to the section.
     * 
     * @param tdl       ToDoList's annotation
     */
    void addAnnotation(ToDoList tdl);
    /**
     * Remove the ToDoList's section from the list.
     * 
     * @param tdl       the annotation to remove
     */
    void removeAnnotation(ToDoList tdl);
    /**
     * Set as done or unset the annotation of the ToDoList in input: if the box is setted the method unsets it, so on the other side.
     * 
     * @param tdl       ToDoList's section : is the box is choosed, then
     */
    void changeBoxStatus(ToDoList tdl);
    /**
     * Return all the annotation saved.
     * 
     * @return  a list of all ToDoList's sections
     */
    List<ToDoList> getAnnotations();
}
