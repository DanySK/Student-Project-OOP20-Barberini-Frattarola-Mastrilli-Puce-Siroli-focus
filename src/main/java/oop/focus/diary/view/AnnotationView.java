package oop.focus.diary.view;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

/**
 * The interface manage the view of ToDoActions in the section of ToDoList.
 */
public interface AnnotationView {
    /**
     * Return a listView of checkBox, each of witch represents a ToDoAction.
     * @return  a listView with all ToDoAction saved
     */
    ListView<CheckBox> getListView();

}
