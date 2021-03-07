package oop.focus.diary.model;
/**
 * The interface ToDoList defines an annotation's structure.
 * It defines methods to set/get the annotation.
 *
 */
public interface ToDoList {
    /**
     * Return the annotation of ToDoList's section.
     * @return  the annotation of ToDoList's section
     */
    String getAnnotation();
    /**
     * Sets the annotation of a ToDoList's section.
     * @param annotation        the annotation to be added
     */
    void setAnnotation(String annotation);
    /**
     * Return a boolean that is true if the annotation is done, false otherwise.
     * @return  if the annotation is done
     */
    boolean isDone();
    /**
     * Sets if the annotation of the ToDoList's section is done or less.
     * @param done      true if the annotation is done, false otherwise.
     */
    void setDone(boolean done);

}
