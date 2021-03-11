package oop.focus.finance;

import oop.focus.homepage.model.Person;

import java.util.List;

public class GroupTransactionImpl implements GroupTransaction {

    private final Person madeBy;
    private final List<Person> forList;
    private final int amount;

    public GroupTransactionImpl(final Person madeBy, final List<Person> forList, final int amount) {
        this.madeBy = madeBy;
        this.forList = forList;
        this.amount = amount;
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
}
