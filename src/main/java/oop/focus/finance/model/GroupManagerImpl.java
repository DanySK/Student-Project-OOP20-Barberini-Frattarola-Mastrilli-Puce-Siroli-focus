package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.homepage.model.Person;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

/**
 * Immutable implementation of a group manager.
 */
public class GroupManagerImpl implements GroupManager {

    private final Dao<Person> group;
    private final Dao<Person> persons;
    private final Dao<GroupTransaction> transactions;

    public GroupManagerImpl(final DataSource db) {
        this.group = db.getGroup();
        this.persons = db.getPersons();
        this.transactions = db.getGroupTransactions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addPerson(final Person person) {
        try {
            this.group.save(person);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removePerson(final Person person) {
        if (this.getCredit(person) == 0) {
            try {
                this.group.delete(person);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final int getCredit(final Person person) {
        return this.transactions.getAll().stream()
                .filter(t -> t.getMadeBy().equals(person))
                .map(GroupTransaction::getAmount)
                .reduce(0, Integer::sum) - this.transactions.getAll().stream()
                .filter(t -> t.getForList().contains(person))
                .map(t -> t.getAmount() / t.getForList().size())
                .reduce(0, Integer::sum);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addTransaction(final GroupTransaction groupTransaction) {
        try {
            this.transactions.save(groupTransaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeTransaction(final GroupTransaction groupTransaction) {
        try {
            this.transactions.delete(groupTransaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final List<GroupTransaction> resolveList() {
        final var ret = new ArrayList<GroupTransaction>();
        final Map<Person, Integer> map = new HashMap<>();
        this.group.getAll().forEach(p -> map.put(p, this.getCredit(p)));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final void reset() {
        new ArrayList<>(this.group.getAll()).forEach(this::removePerson);
        new ArrayList<>(this.transactions.getAll()).forEach(this::removeTransaction);
    }

    @Override
    public final ObservableSet<Person> getGroup() {
        return this.group.getAll();
    }

    @Override
    public final ObservableSet<GroupTransaction> getTransactions() {
        return this.transactions.getAll();
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
