package oop.focus.finance;

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
    public final void execute(final int amount) {
        this.amount = this.amount + amount;
    }

}
