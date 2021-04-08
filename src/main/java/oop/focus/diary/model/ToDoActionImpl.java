package oop.focus.diary.model;

public class ToDoActionImpl implements ToDoAction {
    private static final int MAX_LENGTH = 50;
    private String annotation;
    private boolean done;

    public ToDoActionImpl(final String annotation, final boolean done) {
        if (this.checkSize(annotation)) {
            this.annotation = annotation;
            this.done = done;
        }
    }
    private boolean checkSize(final String annotation) {
        if (annotation.chars().count() > MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
        return true;
    }
    @Override
    public final String getAnnotation() {
        return this.annotation;
    }

    @Override
    public final boolean isDone() {
        return this.done;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.annotation == null) ? 0 : this.annotation.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final ToDoActionImpl other = (ToDoActionImpl) obj;
        if (this.annotation == null) {
            return other.annotation == null;
        } else {
            return this.annotation.equals(other.annotation);
        }
    }
}
