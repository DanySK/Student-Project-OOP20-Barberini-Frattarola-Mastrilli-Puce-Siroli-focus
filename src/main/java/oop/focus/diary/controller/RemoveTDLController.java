package oop.focus.diary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.WindowRemoveAnnotation;

/**
 * Implementation of {@link Controller}. This class manages the elimination of toDoList's annotation.
 * its relative View.
 */
public class RemoveTDLController implements Controller {
    private final View content;

    /**
     * Instantiates a new remove toDoList controller and creates the associated view.
     * @param controller    the toDoListController
     */
    public RemoveTDLController(final ToDoListController controller) {
        final ObservableList<String> list = FXCollections.observableArrayList();
        controller.allAnnotations().forEach(s -> list.add(s.getAnnotation()));
        this.content = new WindowRemoveAnnotation<>(controller, list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.content;
    }
}
