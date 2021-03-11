package oop.focus.finance;

import oop.focus.homepage.model.Person;
import org.joda.time.LocalDate;

import java.util.List;

public class GroupTransactionImpl implements GroupTransaction {

    private final String description;
    private final Person madeBy;
    private final List<Person> forList;
    private final int amount;
    private final LocalDate date;

    public GroupTransactionImpl(final String description, final Person madeBy, final List<Person> forList, final int amount, final LocalDate date) {
        this.description = description;
        this.madeBy = madeBy;
        this.forList = forList;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public final String getDesc() {
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
    public final LocalDate getDate() {
        return this.date;
    }
}
