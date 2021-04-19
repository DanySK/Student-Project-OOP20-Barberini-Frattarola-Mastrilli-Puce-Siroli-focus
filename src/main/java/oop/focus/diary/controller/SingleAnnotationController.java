package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.model.ToDoAction;
import oop.focus.diary.view.SingleToDoActionView;

/**
 * SingleAnnotationController manages single annotation of ToDoList.
 */
public class SingleAnnotationController implements Controller {
    private final View view;

    /**
     * Instantiates a new single annotation controller and creates the associated view.
     *
     * @param action    the annotation to create
     */
    public SingleAnnotationController(final ToDoAction action) {
        this.view = new SingleToDoActionView(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }
}
