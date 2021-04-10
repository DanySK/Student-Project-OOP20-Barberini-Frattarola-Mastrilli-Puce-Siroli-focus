package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.bases.SubscriptionsViewImpl;

public class SubscriptionsControllerImpl implements SubscriptionsController {

    private final SubscriptionsViewImpl view;
    private final FinanceManager manager;

    private final ObservableSet<Transaction> transactions;

    public SubscriptionsControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new SubscriptionsViewImpl(this);
        this.showSubscriptions();
        this.transactions = this.manager.getTransactionManager().getTransactions();
        this.addListeners();
    }

    private void addListeners() {
        this.transactions.addListener((SetChangeListener<Transaction>) change -> this.showSubscriptions());
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
    public final void showSubscriptions() {
        this.view.showSubscriptions(this.manager.getTransactionManager().getSubscriptions());
    }

    @Override
    public final void stopSubscription(final Transaction subscription) {
        this.manager.getTransactionManager().stopRepeat(subscription);
    }
}
