package oop.focus.diary.model;

public class ToDoActionImpl implements ToDoAction {
    private String annotation;
    private boolean done;

    public ToDoActionImpl(final String annotation) {
        this.annotation = annotation;
        this.done = false;
    }

    @Override
    public final String getAnnotation() {
        return this.annotation;
    }

    @Override
    public final void setAnnotation(final String annotation) {
        this.annotation = annotation;
    }
    @Override
    public final boolean isDone() {
        return this.done;
    }
    @Override
    public final void setDone(final boolean done) {
        this.done = done;
    }
}
