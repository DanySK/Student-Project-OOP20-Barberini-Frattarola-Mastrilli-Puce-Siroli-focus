package oop.focus.finance.model;

import java.util.Objects;

/**
 * Immutable implementation of a category.
 */
public class AccountImpl implements Account {

    private final String name;
    private final String color;
    private final int initialAmount;

    public AccountImpl(final String name, final String color, final int initialAmount) {
        this.name = name;
        this.color = color;
        this.initialAmount = initialAmount;
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
    public final int getInitialAmount() {
        return this.initialAmount;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final var account = (AccountImpl) o;
        return Objects.equals(this.name, account.name);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name);
    }
}
