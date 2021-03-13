package oop.focus.finance;

import java.util.Objects;

public class AccountImpl implements Account {

    private final String name;
    private final String color;
    private int amount;

    public AccountImpl(final String name, final String color, final int amount) {
        this.name = name;
        this.color = color;
        this.amount = amount;
    }

    @Override
    public final void execute(final int amount) {
        this.amount = this.amount + amount;
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
    public final int getAmount() {
        return this.amount;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var account = (AccountImpl) o;
        return Objects.equals(this.name, account.name);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name);
    }
}
