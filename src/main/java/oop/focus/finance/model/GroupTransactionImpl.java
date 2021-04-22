package oop.focus.finance.model;

import oop.focus.calendar.persons.model.Person;
import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Immutable implementation of a group transaction.
 */
public class GroupTransactionImpl implements GroupTransaction {

    private final String description;
    private final Person madeBy;
    private final List<Person> forList;
    private final int amount;
    private final LocalDateTime date;

    public GroupTransactionImpl(final String description, final Person madeBy, final List<Person> forList,
                                final int amount, final LocalDateTime date) {
        this.description = description;
        this.madeBy = madeBy;
        this.forList = forList;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final Person getMadeBy() {
        return this.madeBy;
    }

    @Override
    public final List<Person> getForList() {
        return this.forList;
    }

    @Override
    public final int getAmount() {
        return this.amount;
    }

    @Override
    public final LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final var that = (GroupTransactionImpl) o;
        return this.amount == that.amount && Objects.equals(this.description, that.description)
               && Objects.equals(this.madeBy, that.madeBy) && Objects.equals(this.forList, that.forList)
               && Objects.equals(this.date, that.date);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.description, this.madeBy, this.forList, this.amount, this.date);
    }

    @Override
    public final String toString() {
        return "Description: " + this.description
                + ", made by: " + this.madeBy.getName()
                + ", for: " + this.forList.stream().map(Person::getName).collect(Collectors.joining("- "))
                + ", amount: " + this.amount
                + ", date: " + this.date;
    }
}
