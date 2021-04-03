package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.scene.Parent;
import oop.focus.db.DataSource;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.TransactionsView;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionsControllerImpl implements TransactionsController {

    private final TransactionsView view;
    private final FinanceManager manager;
    private final Predicate<Transaction> predicate;

    public TransactionsControllerImpl(final DataSource db, final Predicate<Transaction> predicate) {
        this.view = new TransactionsView(this);
        this.manager = new FinanceManagerImpl(db);
        this.predicate = predicate;
    }

    @Override
    public final ObservableSet<Account> getAccounts() {
        return this.manager.getAccountManager().getAccounts();
    }

    @Override
    public final double getAmount(final Predicate<Account> predicate) {
        return this.filteredAmount(predicate);
    }

    @Override
    public final void showTransactions(final Predicate<Account> predicate) {
        this.view.updateTransactions(this.filteredTransactions(predicate), predicate);
    }

    @Override
    public final Parent getView() {
        return this.view.getRoot();
    }

    private Set<Transaction> filteredTransactions(final Predicate<Account> predicate) {
        return this.manager.getTransactionManager().getTransactions().stream()
                .filter(t -> t.getAccount().equals(this.manager.getAccountManager().getAccounts().stream()
                        .filter(predicate)
                        .collect(Collectors.toList()).get(0)))
                .filter(this.predicate)
                .collect(Collectors.toSet());
    }

    private double filteredAmount(final Predicate<Account> predicate) {
        return (double) this.manager.getAccountManager().getAccounts().stream()
                .filter(predicate)
                .map(this.manager::getAmount)
                .mapToInt(i -> i)
                .sum() / 100;
    }
}
