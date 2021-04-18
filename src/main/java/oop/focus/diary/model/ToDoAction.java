package oop.focus.diary.model;
/**
 * The interface ToDoAction defines an annotation's structure.
 * It defines methods to set/get the annotation.
 *
 */
public interface ToDoAction {
    /**
     * Return the annotation of ToDoList's section.
     * @return  the annotation of ToDoList's section
     */
    String getAnnotation();
    /**
     * Return a boolean that is true if the annotation is done, false otherwise.
     * @return  if the annotation is done
     */
    boolean isDone();
}
