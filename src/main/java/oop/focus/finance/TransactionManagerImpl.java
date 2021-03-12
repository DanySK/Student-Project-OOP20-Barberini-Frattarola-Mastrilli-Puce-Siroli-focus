package oop.focus.finance;

import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionManagerImpl implements TransactionManager {

    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public final void add(final Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public final void remove(final Transaction transaction) {
        this.transactions.remove(transaction);
    }

    @Override
    public final void removeAll(final List<Transaction> transactions) {
        transactions.forEach(this::remove);
    }

    @Override
    public final List<Transaction> getGeneratedTransactions() {
        return this.transactions.stream().flatMap(t -> this.generateNext(t).stream()).collect(Collectors.toList());
    }

    private List<Transaction> generateNext(final Transaction t) {
        if (t.isLast() || LocalDate.now().isBefore(t.getNextRenewal())) {
            return new ArrayList<>();
        }
        t.setLast(true);
        var transaction = new TransactionImpl(t.getDesc(), t.getCat(), t.getNextRenewal(), t.getAccount(), t.getAmount(), t.getRep(), false);
        return Stream.concat(List.of(transaction).stream(), this.generateNext(transaction).stream()).collect(Collectors.toList());
    }

    @Override
    public final List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public final List<Transaction> getIncomes() {
        return this.filteredTransactions(t -> t.getAmount() > 0);
    }

    @Override
    public final List<Transaction> getOutings() {
        return this.filteredTransactions(t -> t.getAmount() < 0);
    }

    @Override
    public final List<Transaction> getSubscriptions() {
        return this.filteredTransactions(t -> !t.isLast());
    }

    @Override
    public final int monthlyExpense() {
        return this.transactions.stream()
                                .filter(t -> !t.isLast())
                                .map(t -> (int) (t.getAmount() / t.getRep().getPerMonth()))
                                .reduce(0, Integer::sum);
    }

    @Override
    public final int yearlyExpense() {
        return this.transactions.stream()
                                .filter(t -> !t.isLast())
                                .map(t -> (int) (t.getAmount() * t.getRep().getPerYear()))
                                .reduce(0, Integer::sum);
    }

    private List<Transaction> filteredTransactions(final Predicate<Transaction> predicate) {
        return this.transactions.stream().filter(predicate).collect(Collectors.toList());
    }
}
