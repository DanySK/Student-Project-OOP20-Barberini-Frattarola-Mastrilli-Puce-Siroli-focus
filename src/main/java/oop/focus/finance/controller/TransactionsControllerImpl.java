package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.bases.TransactionsView;
import oop.focus.finance.view.bases.TransactionsViewImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Implementation of a transactions controller.
 */
public class TransactionsControllerImpl implements TransactionsController {

    private final TransactionsView view;
    private final FinanceManager manager;
    private final Predicate<Transaction> transactionPredicate;
    private Predicate<Account> accountPredicate;

    private final ObservableSet<Transaction> transactions;
    private final ObservableSet<Account> acccounts;

    public TransactionsControllerImpl(final FinanceManager manager, final Predicate<Transaction> predicate) {
        this.manager = manager;
        this.transactionPredicate = predicate;
        this.accountPredicate = a -> true;
        this.view = new TransactionsViewImpl(this);
        this.showTransactions(a -> true);
        this.transactions = this.manager.getTransactionManager().getTransactions();
        this.acccounts = this.getAccounts();
        this.addListeners();
    }

    private void addListeners() {
        this.transactions.addListener((SetChangeListener<Transaction>) c -> this.showTransactions(this.accountPredicate));
        this.acccounts.addListener((SetChangeListener<Account>) c -> this.view.populate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void showTransactions(final Predicate<Account> predicate) {
        this.accountPredicate = predicate;
        this.view.updateTransactions(this.filteredTransactions(predicate).stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList()), predicate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void deleteAccounts() {
        this.getAccounts().stream()
                .filter(this.accountPredicate)
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(this.manager::removeAccount);
        this.showTransactions(a -> true);
    }

    /**
     * {@inheritDoc}
     */
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
        final List<Account> filteredAccounts = this.getAccounts().stream()
                .filter(this.accountPredicate)
                .collect(Collectors.toCollection(ArrayList::new));
        return filteredAccounts.size() == 1 ? filteredAccounts.get(0).getName() : "Tutti i conti";
    }

    @Override
    public final String getColor(final Predicate<Account> predicate) {
        final List<Account> list = this.getAccounts().stream().filter(predicate).collect(Collectors.toList());
        return list.size() == 1 ? list.get(0).getColor() : "ffffff";
    }

    @Override
    public final ObservableSet<Account> getAccounts() {
        return this.manager.getAccountManager().getAccounts();
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
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
