package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.scene.Parent;
import oop.focus.db.DataSource;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.finance.view.TransactionsView;

import java.util.stream.Collectors;

public class TransactionsControllerImpl implements TransactionsController {

    private final TransactionsView view;
    private final FinanceManager manager;

    public TransactionsControllerImpl(final DataSource db) {
        this.view = new TransactionsView(this);
        this.manager = new FinanceManagerImpl(db);
    }

    @Override
    public final double getAmount() {
        return (double) this.manager.getAccountManager().getAccounts().stream()
                .map(this.manager::getAmount)
                .mapToInt(i -> i)
                .sum() / 100;
    }

    @Override
    public final ObservableSet<Account> getAccounts() {
        return this.manager.getAccountManager().getAccounts();
    }

    @Override
    public final void showTransactions() {
        this.view.updateTransactions(this.manager.getTransactionManager().getTransactions());
    }

    @Override
    public final void showTransactions(final Account account) {
        this.view.updateTransactions(this.manager.getTransactionManager().getTransactions().stream()
                .filter(t -> t.getAccount().equals(account))
                .collect(Collectors.toSet()));
    }

    @Override
    public final Parent getView() {
        return this.view.getRoot();
    }
}
