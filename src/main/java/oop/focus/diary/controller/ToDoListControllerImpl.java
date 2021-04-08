package oop.focus.diary.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.model.ToDoAction;
import oop.focus.diary.model.ToDoActionImpl;
import oop.focus.diary.model.ToDoListManager;
import oop.focus.diary.model.ToDoListManagerImpl;

public class ToDoListControllerImpl implements ToDoListController {
    private final ToDoListManager manager;
    private final ObservableList<ToDoAction> toDoActions;

    public ToDoListControllerImpl() {
        this.manager = new ToDoListManagerImpl(new DataSourceImpl());
        this.toDoActions = FXCollections.observableArrayList(this.manager.getAnnotations());
    }
    @Override
    public final ObservableList<ToDoAction> allAnnotations() {
        return this.toDoActions;
    }
    @Override
    public final void addNote(final String annotation) {
        if (this.allAnnotations().stream().noneMatch(a -> a.getAnnotation().equals(annotation))) {
            final ToDoAction ac = new ToDoActionImpl(annotation, false);
            this.toDoActions.add(ac);
            this.manager.addAnnotation(ac);
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
    @Override
    public final void changeCheck(final String a) {
        if (this.allAnnotations().contains(this.findTDAbyString(a))) {
            this.manager.changeBoxStatus(this.findTDAbyString(a));
        }
    }
    @Override
    public final void remove(final String a) {
        this.manager.removeAnnotation(this.findTDAbyString(a));
        this.toDoActions.remove(this.findTDAbyString(a));
    }
}
