package oop.focus.finance.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.AccountImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.TransactionsViewImpl;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionsControllerImpl implements TransactionsController {

    private final TransactionsViewImpl view;
    private final FinanceManager manager;
    private final Predicate<Transaction> predicate;

    public TransactionsControllerImpl(final FinanceManager manager, final Predicate<Transaction> predicate) {
        this.manager = manager;
        this.predicate = predicate;
        this.view = new TransactionsViewImpl(this);
        this.showTransactions(a -> true);
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
    public final void deleteTransaction(final Transaction transaction) {
        this.manager.removeTransaction(transaction);
    }

    @Override
    public final void newAccount(final String name, final String color, final int amount) {
        this.manager.addAccount(new AccountImpl(name, color, amount * 100));
    }

    @Override
    public final ObservableList<String> getColors() {
        return new ObservableListWrapper<>(new ArrayList<>(this.manager.getColors()));
    }

    @Override
    public final View getView() {
        return this.view;
    }

    private Set<Transaction> filteredTransactions(final Predicate<Account> predicate) {
        return this.manager.getTransactionManager().getTransactions().stream()
                .filter(t -> t.getAccount().equals(this.manager.getAccountManager().getAccounts().stream()
                        .filter(predicate).count() == 1 ? this.manager.getAccountManager().getAccounts().stream()
                        .filter(predicate)
                        .collect(Collectors.toList()).get(0) : t.getAccount()))
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
