package oop.focus.diary.view;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import oop.focus.common.View;
import oop.focus.diary.model.ToDoAction;

/**
 * Implementation of {@link View}. Represents single To Do Action of the section of To Do List, using {@link CheckBox}.
 */
public class SingleToDoActionView implements View {
    private CheckBox box;
    private final ToDoAction action;

    /**
     * Instantiates a new single to do action.
     * @param action    the to do action of which it's set the View
     */
    public SingleToDoActionView(final ToDoAction action ) {
        this.box = new CheckBox();
        this.action = action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        this.box = new CheckBox(this.action.getAnnotation());
        this.box.setSelected(this.action.isDone());
        return this.box;
    }
}
