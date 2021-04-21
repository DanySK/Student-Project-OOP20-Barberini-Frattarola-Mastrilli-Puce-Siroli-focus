package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.WindowCreateNewAnnotation;

/**
 * The controller of the window that manages the adding of a new toDoAction.
 */
public class CreateNewAnnotationController implements Controller {
    private final View content;

    /**
     * Instantiates a new Create new annotation controller.
     * @param controller    to do list controller
     */
    public CreateNewAnnotationController(final ToDoListController controller) {
        this.content = new WindowCreateNewAnnotation(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.content;
    }
}
