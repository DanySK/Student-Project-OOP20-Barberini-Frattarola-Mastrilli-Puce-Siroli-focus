package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.bases.SubscriptionsViewImpl;

import java.util.Comparator;
import java.util.stream.Collectors;

public class SubscriptionsControllerImpl implements SubscriptionsController {

    private static final int GENERIC_PRICE = 12;

    private final SubscriptionsViewImpl view;
    private final FinanceManager manager;

    private final ObservableSet<Transaction> transactions;

    public SubscriptionsControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new SubscriptionsViewImpl(this);
        this.showSortedSubscriptions();
        this.transactions = this.manager.getTransactionManager().getTransactions();
        this.addListeners();
    }

    private void addListeners() {
        this.transactions.addListener((SetChangeListener<Transaction>) change -> {
            this.showSortedSubscriptions();
            this.view.populate();
        });
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final double getYearlyExpense() {
        return (double) this.manager.getTransactionManager().yearlyExpense() / 100;
    }

    @Override
    public final double getMonthlyExpense() {
        return (double) this.manager.getTransactionManager().monthlyExpense() / 100;
    }

    @Override
    public final void showSortedSubscriptions() {
        this.view.showSubscriptions(this.manager.getTransactionManager().getSubscriptions().stream()
                .sorted(Comparator.comparingInt(a -> a.getRepetition().getPerMonthFunction().apply(GENERIC_PRICE)))
                .collect(Collectors.toList()));
    }

    @Override
    public final double getTransactionAmount(final Transaction t) {
        return (double) t.getAmount() / 100;
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }
}
