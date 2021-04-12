package oop.focus.finance.model;

import java.util.Objects;

/**
 * Immutable implementation of a category.
 */
public class CategoryImpl implements Category {

    private final String name;
    private final String color;

    public CategoryImpl(final String name, final String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getColor() {
        return this.color;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final var category = (CategoryImpl) o;
        return Objects.equals(this.name, category.name);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public final String toString() {
        return this.name;
    }
}
