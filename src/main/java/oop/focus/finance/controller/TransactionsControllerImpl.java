package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.AccountImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.bases.TransactionsViewImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionsControllerImpl implements TransactionsController {

    private final TransactionsViewImpl view;
    private final FinanceManager manager;
    private final Predicate<Transaction> transactionPredicate;
    private Predicate<Account> accountPredicate;

    private final ObservableSet<Transaction> transactions;
    private final ObservableSet<Account> acccounts;

    public TransactionsControllerImpl(final FinanceManager manager, final Predicate<Transaction> predicate) {
        this.manager = manager;
        this.transactionPredicate = predicate;
        this.accountPredicate = (a -> true);
        this.view = new TransactionsViewImpl(this);
        this.showTransactions(a -> true);
        this.transactions = this.manager.getTransactionManager().getTransactions();
        this.acccounts = this.getAccounts();
        this.addListeners();
    }

    private void addListeners() {
        this.transactions.addListener((SetChangeListener<Transaction>) c ->
                this.showTransactions(this.accountPredicate));
        this.acccounts.addListener((SetChangeListener<Account>) c ->
                this.view.populate());
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void showTransactions(final Predicate<Account> predicate) {
        this.accountPredicate = predicate;
        this.view.updateTransactions(this.filteredTransactions(predicate).stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList()), predicate);
    }

    @Override
    public final void newAccount(final String name, final String color, final double amount) {
        this.manager.addAccount(new AccountImpl(name, color, (int) (amount * 100)));
    }

    @Override
    public final void deleteAccounts() {
        this.getAccounts().stream()
                .filter(this.accountPredicate)
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(this.manager::removeAccount);
        this.showTransactions(a -> true);
    }

    @Override
    public final void deleteTransaction(final Transaction transaction) {
        this.manager.removeTransaction(transaction);
    }

    @Override
    public final double getAmount(final Predicate<Account> predicate) {
        return (double) this.filteredAmount(predicate) / 100;
    }

    @Override
    public final String getAccountName() {
        var filteredAccounts = this.getAccounts().stream()
                .filter(this.accountPredicate)
                .collect(Collectors.toCollection(ArrayList::new));
        return filteredAccounts.size() == 1 ? filteredAccounts.get(0).getName() : "Tutti i conti";
    }

    @Override
    public final String getColor(final Predicate<Account> predicate) {
        var list = this.getAccounts().stream().filter(predicate).collect(Collectors.toList());
        return list.size() == 1 ? list.get(0).getColor() : "ffffff";
    }

    @Override
    public final ObservableSet<Account> getAccounts() {
        return this.manager.getAccountManager().getAccounts();
    }

    private Set<Transaction> filteredTransactions(final Predicate<Account> predicate) {
        return this.manager.getTransactionManager().getTransactions().stream()
                .filter(t -> t.getAccount().equals(this.manager.getAccountManager().getAccounts().stream()
                        .filter(predicate).count() == 1 ? this.manager.getAccountManager().getAccounts().stream()
                        .filter(predicate).collect(Collectors.toList()).get(0) : t.getAccount()))
                .filter(this.transactionPredicate)
                .collect(Collectors.toSet());
    }

    private int filteredAmount(final Predicate<Account> predicate) {
        return this.manager.getAccountManager().getAccounts().stream()
                .filter(predicate)
                .map(this.manager::getAmount)
                .mapToInt(i -> i)
                .sum();
    }
}
