package oop.focus.diary.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.diary.model.ToDoAction;
import oop.focus.diary.model.ToDoActionImpl;
import oop.focus.diary.model.ToDoListManager;
import oop.focus.diary.view.ToDoListView;

/**
 * Implementation of {@link ToDoListController}. It has method to create, delete or modify a ToDoAction.
 */
public class ToDoListControllerImpl implements ToDoListController {
    private final ToDoListManager manager;
    private final ObservableSet<ToDoAction> toDoActions;
    private final View content;

    /**
     *  Instantiates a to do list controller and creates the associated view.
     *
     * @param manager   toDoList manager
     */
    public ToDoListControllerImpl(final ToDoListManager manager) {
        this.manager = manager;
        this.toDoActions = FXCollections.observableSet();
        this.toDoActions.addAll(this.manager.getAnnotations());
        this.content = new ToDoListView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableSet<ToDoAction> allAnnotations() {
        return this.toDoActions;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addNote(final String annotation) {
        if (this.toDoActions.stream().noneMatch(a -> a.getAnnotation().equals(annotation))) {
            final ToDoAction ac = new ToDoActionImpl(annotation, false);
            this.manager.addAnnotation(ac);
            this.toDoActions.add(ac);
        }
    }

    /**
     * The method finds the to do action whose name is the string in input.
     * @param s the name of the annotation to found
     * @return  the to do action whose name is the string in input
     */
    private ToDoAction findTDAbyString(final String s) {
        if (this.toDoActions.stream().anyMatch(a -> a.getAnnotation().equals(s))) {
            return this.toDoActions.stream().filter(a -> a.getAnnotation().equals(s)).findAny().get();
        }
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void changeCheck(final String a) {
        if (this.allAnnotations().contains(this.findTDAbyString(a))) {
            this.manager.changeBoxStatus(this.findTDAbyString(a));
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final String a) {
        this.manager.removeAnnotation(this.findTDAbyString(a));
        this.toDoActions.remove(this.findTDAbyString(a));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.content;
    }
}
