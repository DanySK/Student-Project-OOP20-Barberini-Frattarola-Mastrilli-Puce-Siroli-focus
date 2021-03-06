package oop.focus.diary.model;

public class ToDoListImpl implements ToDoList {
    private String annotation;

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
}
