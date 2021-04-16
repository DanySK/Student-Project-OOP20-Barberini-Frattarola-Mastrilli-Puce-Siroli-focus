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

public class GroupManagerImpl implements GroupManager {

    private final Dao<Person> group;
    private final Dao<GroupTransaction> transactions;
    private final Dao<Person> persons;

    public GroupManagerImpl(final DataSource db) {
        this.group = db.getGroup();
        this.transactions = db.getGroupTransactions();
        this.persons = db.getPersons();
    }

    @Override
    public final void addPerson(final Person person) {
        try {
            this.group.save(person);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public final void addTransaction(final GroupTransaction groupTransaction) {
        try {
            this.transactions.save(groupTransaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void removeTransaction(final GroupTransaction groupTransaction) {
        try {
            this.transactions.delete(groupTransaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final List<GroupTransaction> resolve() {
        final var ret = new ArrayList<GroupTransaction>();
        final Map<Person, Integer> map = new HashMap<>();
        this.group.getAll().forEach(p -> map.put(p, this.getCredit(p)));
        while (!map.values().stream().allMatch(i -> i == 0)) {
            final var creditor = this.getCreditor(map);
            final var debtor = this.getDebtor(map);
            final var amount = this.calculateAmount(map);
            ret.add(new GroupTransactionImpl("Risoluzione debiti", debtor,
                    List.of(creditor), amount, LocalDateTime.now()));
            map.replace(creditor, map.get(creditor) - amount);
            map.replace(debtor, map.get(debtor) + amount);
        }
        return ret;
    }

    private int calculateAmount(final Map<Person, Integer> map) {
        return map.get(this.getCreditor(map)) < -map.get(this.getDebtor(map))
                ? map.get(this.getCreditor(map)) : -map.get(this.getDebtor(map));
    }

    private Person getCreditor(final Map<Person, Integer> map) {
        return map.keySet().stream().max(Comparator.comparingInt(map::get)).orElse(null);
    }

    private Person getDebtor(final Map<Person, Integer> map) {
        return map.keySet().stream().filter(p -> map.get(p) < 0).max(Comparator.comparingInt(map::get)).orElse(null);
    }

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
}
