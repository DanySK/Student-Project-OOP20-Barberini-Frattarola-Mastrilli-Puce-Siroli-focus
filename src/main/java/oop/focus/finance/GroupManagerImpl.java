package oop.focus.finance;

import oop.focus.homepage.model.Person;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

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
        var ret = new ArrayList<GroupTransaction>();
        Map<Person, Integer> map = new HashMap<>();
        this.group.forEach(p -> map.put(p, this.getCredit(p)));
        while (!map.values().stream().allMatch(i -> i == 0)) {
            var creditor = this.getCreditor(map);
            var debtor = this.getDebtor(map);
            var amount = this.calculateAmount(map);
            ret.add(new GroupTransactionImpl("Auto", debtor, List.of(creditor), amount, LocalDate.now()));
            map.replace(creditor, map.get(creditor) - amount);
            map.replace(debtor, map.get(debtor) + amount);
        }
        return ret;
    }

    private int calculateAmount(final Map<Person, Integer> map) {
        return (map.get(this.getCreditor(map)) < -map.get(this.getDebtor(map)))
                ? map.get(this.getCreditor(map)) : -map.get(this.getDebtor(map));
    }

    private Person getCreditor(final Map<Person, Integer> map) {
        return map.keySet().stream().max(Comparator.comparingInt(map::get)).get();
    }

    private Person getDebtor(final Map<Person, Integer> map) {
        return map.keySet().stream().filter(p -> map.get(p) < 0).max(Comparator.comparingInt(map::get)).get();
    }

    @Override
    public final void reset() {
        new ArrayList<>(this.group).forEach(this::removePerson);
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
