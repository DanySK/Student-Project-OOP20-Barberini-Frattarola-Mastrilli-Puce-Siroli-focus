package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.RemoveTDLView;
/**
 * Implementation of {@link Controller}. This class manages the elimination of toDoList's annotation.
 * its relative View.
 */
public class RemoveTDLController implements Controller {
    private final View content;

    /**
     * Instantiates a new remove tdl controller and creates the associated view.
     * @param controller    the toDoListController
     */
    public RemoveTDLController(final ToDoListController controller) {
        this.content = new RemoveTDLView(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.content;
    }
}
