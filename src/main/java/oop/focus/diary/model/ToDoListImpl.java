package oop.focus.diary.model;

public class ToDoListImpl implements ToDoList {
    private String annotation;
    private boolean done;

    public ToDoListImpl(final String annotation) {
        super();
        this.annotation = annotation;
    }

    @Override
    public final String getAnnotation() {
        return annotation;
    }

    @Override
    public final void setAnnotation(final String annotation) {
        this.annotation = annotation;
    }
    @Override
    public final boolean isDone() {
        return done;
    }
    @Override
    public final void setDone(final boolean done) {
        this.done = done;
    }
}
