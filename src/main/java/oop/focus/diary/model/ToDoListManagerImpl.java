package oop.focus.diary.model;

import java.util.ArrayList;
import java.util.List;


public class ToDoListManagerImpl implements ToDoListManager {
    private final List<ToDoAction> list;
    public ToDoListManagerImpl() {
        this.list = new ArrayList<>();
    }

    @Override
    public final void addAnnotation(final ToDoAction tdl) {
        if (!this.list.contains(tdl)) {
            this.list.add(tdl);
        }
    }

    @Override
    public final void removeAnnotation(final ToDoAction tdl) {
        this.list.remove(tdl);
    }

    @Override
    public final void changeBoxStatus(final ToDoAction tdl) {
        this.list.stream().filter(l -> l.equals(tdl)).forEach(s ->  s.setDone(!s.isDone()));
    }

    @Override
    public final List<ToDoAction> getAnnotations() {
        return this.list;
    }
}
