package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.QuickTransaction;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.bases.FinanceHomePageView;
import oop.focus.finance.view.bases.FinanceHomePageViewImpl;
import org.joda.time.LocalDate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceHomePageControllerImpl implements FinanceHomePageController {

    private final FinanceHomePageView view;
    private final FinanceManager manager;

    private final ObservableSet<Account> accounts;
    private final ObservableSet<Transaction> transaction;
    private final ObservableSet<QuickTransaction> quickTransactions;

    public FinanceHomePageControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new FinanceHomePageViewImpl(this);
        this.accounts = manager.getAccountManager().getAccounts();
        this.transaction = manager.getTransactionManager().getTransactions();
        this.quickTransactions = manager.getQuickManager().getQuickTransactions();
        this.addListeners();
    }

    private void addListeners() {
        this.accounts.addListener((SetChangeListener<Account>) change -> this.view.populateAccounts());
        this.quickTransactions.addListener((SetChangeListener<QuickTransaction>) change ->
                this.view.populateQuickTransactions());
        this.transaction.addListener((SetChangeListener<Transaction>) change -> {
            this.view.populateRecentTransactions();
            this.view.populateAccounts();
        });
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void doQuickTransaction(final QuickTransaction quickTransaction) {
        this.manager.doQuickTransaction(quickTransaction);
    }

    @Override
    public final void resetQuickTransactions() {
        this.manager.getQuickManager().reset();
    }

    @Override
    public final double getAmount(final Account account) {
        return (double) this.manager.getAmount(account) / 100;
    }

    @Override
    public final double getTotalAmount() {
        return (double) this.manager.getAccountManager().getAccounts().stream()
                .map(this.manager::getAmount)
                .mapToInt(i -> i)
                .sum() / 100;
    }

    @Override
    public final List<Transaction> getSortedTodayTransactions() {
        return this.manager.getTransactionManager().getTransactions().stream()
                .filter(t -> t.getDate().toLocalDate().equals(LocalDate.now()))
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public final List<QuickTransaction> getSortedQuickTransactions() {
        return this.manager.getQuickManager().getQuickTransactions().stream()
                .sorted(Comparator.comparing(QuickTransaction::getDescription))
                .collect(Collectors.toList());
    }

    @Override
    public final List<Account> getSortedAccounts() {
        return this.manager.getAccountManager().getAccounts().stream()
                .sorted(Comparator.comparing(Account::getName))
                .collect(Collectors.toList());
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }
}
