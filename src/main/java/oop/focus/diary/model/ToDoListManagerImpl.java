package oop.focus.diary.model;

import java.util.ArrayList;
import java.util.List;

public class ToDoListManagerImpl implements ToDoListManager {
    private final List<ToDoList> list;
    public ToDoListManagerImpl() {
        super();
        this.list = new ArrayList<>();
    }

    @Override
    public final void addAnnotation(final ToDoList tdl) {
        this.list.add(tdl);
    }

    @Override
    public final void removeAnnotation(final ToDoList tdl) {
        this.list.remove(tdl);
    }

    @Override
    public final void changeBoxStatus(final ToDoList tdl) {
        list.stream().filter(l -> l.equals(tdl)).peek(s -> s.setDone(s.isDone() ? false : true));
    }

    @Override
    public final List<ToDoList> getAnnotations() {
        return this.list;
    }
}
