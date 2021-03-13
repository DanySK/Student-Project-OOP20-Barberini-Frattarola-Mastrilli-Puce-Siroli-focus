package oop.focus.finance;

import oop.focus.homepage.model.Person;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupManagerImpl implements GroupManager {

    private final List<Person> group = new ArrayList<>();
    private final List<GroupTransaction> transactions = new ArrayList<>();

    @Override
    public final void addPerson(final Person person) {
        this.group.add(person);
    }

    @Override
    public final void removePerson(final Person person) {
        if (this.getCredit(person) == 0) {
            this.group.remove(person);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final int getCredit(final Person person) {
        return this.transactions.stream()
                .filter(t -> t.getMadeBy().equals(person))
                .map(GroupTransaction::getAmount)
                .reduce(0, Integer::sum) - this.transactions.stream()
                .filter(t -> t.getForList().contains(person))
                .map(t -> t.getAmount() / t.getForList().size())
                .reduce(0, Integer::sum);
    }

    @Override
    public final void addTransaction(final GroupTransaction groupTransaction) {
        this.transactions.add(groupTransaction);
    }

    @Override
    public final void removeTransaction(final GroupTransaction groupTransaction) {
        this.transactions.remove(groupTransaction);
    }

    @Override
    public final List<GroupTransaction> resolve() {
        // TODO
        return null;
    }

    @Override
    public final void reset() {
        this.group.forEach(this::removePerson);
        this.transactions.clear();
    }

    @Override
    public final List<Person> getGroup() {
        return this.group;
    }

    @Override
    public final List<GroupTransaction> getTransactions() {
        return this.transactions;
    }
}
