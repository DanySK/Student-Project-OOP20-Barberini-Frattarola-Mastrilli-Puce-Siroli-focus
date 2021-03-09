package oop.focus.finance;

import org.joda.time.LocalDate;

public class TransactionImpl implements Transaction {

    private final String description;
    private final Category category;
    private final LocalDate date;
    private final Account account;
    private final int amount;
    private final Repetition repetition;
    private final boolean last;

    public TransactionImpl(final String description, final Category category,
            final LocalDate localDate, final Account account, final int amount,
            final Repetition repetition, final boolean last) {
        this.description = description;
        this.category = category;
        this.date = localDate;
        this.account = account;
        this.amount = amount;
        this.repetition = repetition;
        this.last = last;
    }

    @Override
    public final String getDesc() {
        return this.description;
    }

    @Override
    public final Category getCat() {
        return this.category;
    }

    @Override
    public final Account getAccount() {
        return this.account;
    }

    @Override
    public final Repetition getRep() {
        return this.repetition;
    }

    @Override
    public final Boolean isLast() {
        return this.last;
    }

    @Override
    public final LocalDate getDate() {
        return this.date;
    }

    @Override
    public final int getAmount() {
        return this.amount;
    }

}
