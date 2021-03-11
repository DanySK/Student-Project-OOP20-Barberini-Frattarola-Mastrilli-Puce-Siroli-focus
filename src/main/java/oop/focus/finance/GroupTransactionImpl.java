package oop.focus.finance;

import oop.focus.homepage.model.Person;
import org.joda.time.LocalDate;

import java.util.List;

public class GroupTransactionImpl implements GroupTransaction {

    private final Person madeBy;
    private final List<Person> forList;
    private final int amount;
    private final LocalDate date;

    public GroupTransactionImpl(final Person madeBy, final List<Person> forList, final int amount, final LocalDate date) {
        this.madeBy = madeBy;
        this.forList = forList;
        this.amount = amount;
        this.date = date;
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
