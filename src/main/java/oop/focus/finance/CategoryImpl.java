package oop.focus.finance;

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
}
