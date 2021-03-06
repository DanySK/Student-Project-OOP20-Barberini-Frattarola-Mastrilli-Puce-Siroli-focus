package oop.focus.diary.model;
/**
 * The interface ToDoList defines an annotation's structure.
 * It defines methods to set/get the annotation.
 *
 */
public interface ToDoList {
    /**
     * @return  the annotation of ToDoList's section
     */
    String getAnnotation();
    /**
     * @param annotation        the annotation to be added
     */
    void setAnnotation(String annotation);

}
