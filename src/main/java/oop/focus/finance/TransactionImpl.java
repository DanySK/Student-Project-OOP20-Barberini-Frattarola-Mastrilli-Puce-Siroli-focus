package oop.focus.finance;

import java.util.Date;

public class TransactionImpl implements Transaction {

    private final String description;
    private final Category category;
    private final Date date;
    private final Account account;
    private final int amount;
    private final Repetition repetition;
    private final Boolean last;

    public TransactionImpl(final String description, final Category category,
            final Date date, final Account account, final int amount,
            final Repetition repetition, final Boolean last) {
        this.description = description;
        this.category = category;
        this.date = date;
        this.account = account;
        this.amount = amount;
        this.repetition = repetition;
        this.last = last;
    }

    @Override
    public final String getDescr() {
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
    public final Date getDate() {
        return this.date;
    }

    @Override
    public final int getAmount() {
        return this.amount;
    }

}
