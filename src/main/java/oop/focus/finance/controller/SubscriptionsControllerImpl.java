package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.bases.SubscriptionsViewImpl;

import java.text.DecimalFormat;
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
    public final String getYearlyExpense() {
        return this.format(this.manager.getTransactionManager().yearlyExpense());
    }

    @Override
    public final String getMonthlyExpense() {
        return this.format(this.manager.getTransactionManager().monthlyExpense());
    }

    @Override
    public final void showSortedSubscriptions() {
        this.view.showSubscriptions(this.manager.getTransactionManager().getSubscriptions().stream()
                .sorted(Comparator.comparingInt(a -> a.getRepetition().getPerMonthFunction().apply(GENERIC_PRICE)))
                .collect(Collectors.toList()));
    }

    @Override
    public final void stopSubscription(final Transaction subscription) {
        this.manager.getTransactionManager().stopRepeat(subscription);
    }

    @Override
    public final String getTransactionAmount(final Transaction t) {
        return this.format(t.getAmount());
    }

    private String format(final int amount) {
        final DecimalFormat f = new DecimalFormat("#0.00");
        return f.format((double) amount / 100);
    }
}
