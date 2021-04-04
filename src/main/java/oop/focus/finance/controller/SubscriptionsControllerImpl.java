package oop.focus.finance.controller;

import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.view.SubscriptionsViewImpl;
import oop.focus.finance.view.View;

public class SubscriptionsControllerImpl implements SubscriptionsController {

    private final SubscriptionsViewImpl view;
    private final FinanceManager manager;

    public SubscriptionsControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new SubscriptionsViewImpl(this);
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
}
