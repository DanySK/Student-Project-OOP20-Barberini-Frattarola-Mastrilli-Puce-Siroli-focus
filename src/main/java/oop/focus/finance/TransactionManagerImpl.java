package oop.focus.finance;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionManagerImpl implements TransactionManager {

    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public final void add(final Transaction transaction) {
        if (!transaction.getDate().isAfter(LocalDate.now())) {
            this.transactions.add(transaction);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public final void remove(final Transaction transaction) {
        this.transactions.remove(transaction);
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
        return this.filteredTransactions(Transaction::isToBeRepeated);
    }

    @Override
    public final int monthlyExpense() {
        return this.computeExpense(Repetition::getPerMonthFunction);
    }

    @Override
    public final int yearlyExpense() {
        return this.computeExpense(Repetition::getPerYearFunction);
    }

    private Integer computeExpense(final Function<Repetition, Function<Integer, Integer>> function) {
        return this.transactions.stream()
                .filter(Transaction::isToBeRepeated)
                .map(t -> function.apply(t.getRepetition()).apply(t.getAmount()))
                .reduce(0, Integer::sum);
    }

    @Override
    public final List<Transaction> getGeneratedTransactions(final LocalDate date) {
        return this.transactions.stream().flatMap(t -> this.generateNext(t, date).stream()).collect(Collectors.toList());
    }

    private List<Transaction> generateNext(final Transaction t, final LocalDate date) {
        if (!t.isToBeRepeated() || date.isBefore(t.getNextRenewal())) {
            return new ArrayList<>();
        }
        t.stopRepeat();
        var transaction = new TransactionImpl(t.getDescription(), t.getCategory(), t.getNextRenewal(),
                t.getAccount(), t.getAmount(), t.getRepetition());
        return Stream.concat(List.of(transaction).stream(),
                this.generateNext(transaction, date).stream()).collect(Collectors.toList());
    }

    private List<Transaction> filteredTransactions(final Predicate<Transaction> predicate) {
        return this.transactions.stream().filter(predicate).collect(Collectors.toList());
    }
}
