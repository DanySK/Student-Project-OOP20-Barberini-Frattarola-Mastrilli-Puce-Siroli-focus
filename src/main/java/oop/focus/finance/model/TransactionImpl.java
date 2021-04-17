package oop.focus.finance.model;

import oop.focus.common.Repetition;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.text.DecimalFormat;
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
                           final LocalDateTime localDateTime, final Account account, final int amount,
                           final Repetition repetition, final boolean toRepeat) {
        this.description = description;
        this.category = category;
        this.date = localDateTime;
        this.account = account;
        this.amount = amount;
        this.repetition = repetition;
        this.toRepeat = toRepeat;
    }

    public TransactionImpl(final String description, final Category category,
                           final LocalDateTime localDate, final Account account, final int amount,
                           final Repetition repetition) {
        this(description, category, localDate, account, amount, repetition, !repetition.equals(Repetition.ONCE));
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
    public final String getDateToString() {
        final DecimalFormat df = new DecimalFormat("#00");
        return this.date.get(DateTimeFieldType.year()) + "/" + this.date.get(DateTimeFieldType.monthOfYear()) + "/"
                + this.date.get(DateTimeFieldType.dayOfMonth()) + "  " + df.format(this.date.get(DateTimeFieldType.hourOfDay()))
                + ":" + df.format(this.date.get(DateTimeFieldType.minuteOfHour()));
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final TransactionImpl that = (TransactionImpl) o;
        return this.amount == that.amount && Objects.equals(this.description, that.description)
                && Objects.equals(this.category, that.category) && Objects.equals(this.date, that.date)
                && Objects.equals(this.account, that.account) && this.repetition == that.repetition;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.description, this.category, this.date, this.account, this.amount, this.repetition);
    }

    @Override
    public final String toString() {
        return "Description: " + this.description
                + ", category: " + this.category.getName()
                + ", date: " + this.getDateToString()
                + ", account: " + this.account.getName()
                + ", amount: " + this.amount
                + ", repetition: " + this.repetition.getName();
    }
}
