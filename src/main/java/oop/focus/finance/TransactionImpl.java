package oop.focus.finance;

import org.joda.time.LocalDate;

import java.util.Objects;

public class TransactionImpl implements Transaction {

    private final String description;
    private final Category category;
    private LocalDate date;
    private final Account account;
    private final int amount;
    private final Repetition repetition;
    private boolean last;

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
    public final void setLast(final boolean b) {
        this.last = b;
    }

    @Override
    public final LocalDate getDate() {
        return this.date;
    }

    @Override
    public final int getAmount() {
        return this.amount;
    }

    @Override
    public final LocalDate getNextRenewal() {
        return this.repetition.getFunction().apply(this.date);
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TransactionImpl that = (TransactionImpl) o;
        return this.amount == that.amount && Objects.equals(this.description, that.description)
                && Objects.equals(this.category, that.category) && Objects.equals(this.date, that.date)
                && Objects.equals(this.account, that.account) && this.repetition == that.repetition;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.description, this.category, this.date, this.account, this.amount, this.repetition);
    }
}
