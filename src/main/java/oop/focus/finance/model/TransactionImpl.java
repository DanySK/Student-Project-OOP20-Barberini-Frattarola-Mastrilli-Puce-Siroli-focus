package oop.focus.finance.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Objects;

public class TransactionImpl implements Transaction {

    private final String description;
    private final Category category;
    private final LocalDateTime date;
    private final Account account;
    private final int amount;
    private final Repetition repetition;
    private boolean toRepeat;

    public TransactionImpl(final String description, final Category category,
                           final LocalDateTime localDate, final Account account, final int amount,
                           final Repetition repetition, final boolean toRepeat) {
        this.description = description;
        this.category = category;
        this.date = localDate;
        this.account = account;
        this.amount = amount;
        this.repetition = repetition;
        this.toRepeat = toRepeat;
    }

    public TransactionImpl(final String description, final Category category,
                           final LocalDateTime localDate, final Account account, final int amount,
                           final Repetition repetition) {
        this.description = description;
        this.category = category;
        this.date = localDate;
        this.account = account;
        this.amount = amount;
        this.repetition = repetition;
        this.toRepeat = !repetition.equals(Repetition.ONCE);
    }

    @Override
    public final LocalDate getNextRenewal() {
        return this.repetition.getNextRenewalFunction().apply(new LocalDate(this.date.getYear(), this.date.getMonthOfYear(), this.date.getDayOfMonth()));
    }

    @Override
    public final void stopRepeat() {
        this.toRepeat = false;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final Category getCategory() {
        return this.category;
    }

    @Override
    public final LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public final Account getAccount() {
        return this.account;
    }

    @Override
    public final int getAmount() {
        return this.amount;
    }

    @Override
    public final Repetition getRepetition() {
        return this.repetition;
    }

    @Override
    public final Boolean isToBeRepeated() {
        return this.toRepeat;
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
