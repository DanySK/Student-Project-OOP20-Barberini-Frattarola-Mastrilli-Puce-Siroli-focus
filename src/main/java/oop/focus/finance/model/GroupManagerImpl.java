package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Person;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

/**
 * Immutable implementation of a group manager.
 * The manager deals with the management of both the people present in the group and the group transactions.
 */
public class GroupManagerImpl implements GroupManager {

    private final Manager<GroupTransaction> transactions;
    private final Manager<Person> group;
    private final Dao<Person> persons;

    public GroupManagerImpl(final DataSource db) {
        this.transactions = new GroupTransactionManagerImpl(db);
        this.group = new FinanceGroupManagerImpl(db);
        this.persons = db.getPersons();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addPerson(final Person person) {
        this.group.add(person);
    }

    /**
     * {@inheritDoc}
     */
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
        return this.getTransactions().stream()
                .filter(t -> t.getMadeBy().equals(person))
                .map(GroupTransaction::getAmount)
                .reduce(0, Integer::sum) - this.getTransactions().stream()
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
    public final List<GroupTransaction> resolveList() {
        final var ret = new ArrayList<GroupTransaction>();
        final Map<Person, Integer> map = new HashMap<>();
        this.getGroup().forEach(p -> map.put(p, this.getCredit(p)));
        while (!map.values().stream().allMatch(i -> i == 0)) {
            final Person creditor = this.getCreditor(map);
            final Person debtor = this.getDebtor(map);
            final int amount = this.calculateAmount(map);
            ret.add(new GroupTransactionImpl("Risoluzione debiti", debtor,
                    List.of(creditor), amount, LocalDateTime.now()));
            map.replace(creditor, map.get(creditor) - amount);
            map.replace(debtor, map.get(debtor) + amount);
        }
        return ret;
    }

    @Override
    public final ObservableSet<Person> getGroup() {
        return this.group.getElements();
    }

    @Override
    public final ObservableSet<GroupTransaction> getTransactions() {
        return this.transactions.getElements();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void reset() {
        new ArrayList<>(this.getGroup()).forEach(this::removePerson);
        new ArrayList<>(this.getTransactions()).forEach(this::removeTransaction);
    }

    @Override
    public final ObservableSet<Person> getPersons() {
        return this.persons.getAll();
    }

    /**
     * @param map of people with their own credit
     * @return the absolute minimum value between the maximum debt and the maximum credit
     */
    private int calculateAmount(final Map<Person, Integer> map) {
        return Math.min(map.get(this.getCreditor(map)), -map.get(this.getDebtor(map)));
    }

    /**
     * @param map of people with their own credit
     * @return the person with the greatest credit
     */
    private Person getCreditor(final Map<Person, Integer> map) {
        return map.keySet().stream().max(Comparator.comparingInt(map::get)).orElse(null);
    }

    /**
     * @param map of people with their own credit
     * @return the person with the greatest debt
     */
    private Person getDebtor(final Map<Person, Integer> map) {
        return map.keySet().stream()
                .filter(p -> map.get(p) < 0)
                .max(Comparator.comparingInt(map::get))
                .orElse(null);
    }
}
