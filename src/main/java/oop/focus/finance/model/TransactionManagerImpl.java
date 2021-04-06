package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.common.Repetition;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionManagerImpl implements TransactionManager {

    private final Dao<Transaction> transactions;

    public TransactionManagerImpl(final DataSource db) {
        this.transactions = db.getTransactions();
    }

    @Override
    public final void add(final Transaction transaction) {
        if (!transaction.getDate().isAfter(LocalDateTime.now())) {
            try {
                this.transactions.save(transaction);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public final void remove(final Transaction transaction) {
        try {
            this.transactions.delete(transaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void update(final Transaction transaction) {
        this.remove(transaction);
        this.add(transaction);
    }

    @Override
    public final void stopRepeat(final Transaction subscription) {
        subscription.stopRepeat();
        this.update(subscription);
    }

    @Override
    public final ObservableSet<Transaction> getTransactions() {
        return this.transactions.getAll();
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
        return this.transactions.getAll().stream()
                .filter(Transaction::isToBeRepeated)
                .map(t -> function.apply(t.getRepetition()).apply(t.getAmount()))
                .reduce(0, Integer::sum);
    }

    @Override
    public final List<Transaction> getGeneratedTransactions(final LocalDate date) {
        return this.transactions.getAll().stream()
                .flatMap(t -> this.generateNext(t, date).stream()).collect(Collectors.toList());
    }

    private List<Transaction> generateNext(final Transaction t, final LocalDate date) {
        if (!t.isToBeRepeated() || new LocalDateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), 0, 0, 0)
                .isBefore(new LocalDateTime(t.getNextRenewal().getYear(), t.getNextRenewal().getMonthOfYear(), t.getNextRenewal().getDayOfMonth(), 0, 0, 0))) {
            return new ArrayList<>();
        }
        t.stopRepeat();
        final var transaction = new TransactionImpl(t.getDescription(), t.getCategory(),
                new LocalDateTime(t.getNextRenewal().getYear(), t.getNextRenewal().getMonthOfYear(), t.getNextRenewal().getDayOfMonth(), 0, 0, 0),
                t.getAccount(), t.getAmount(), t.getRepetition());
        return Stream.concat(List.of(transaction).stream(),
                this.generateNext(transaction, date).stream()).collect(Collectors.toList());
    }

    private List<Transaction> filteredTransactions(final Predicate<Transaction> predicate) {
        return this.transactions.getAll().stream().filter(predicate).collect(Collectors.toList());
    }
}
