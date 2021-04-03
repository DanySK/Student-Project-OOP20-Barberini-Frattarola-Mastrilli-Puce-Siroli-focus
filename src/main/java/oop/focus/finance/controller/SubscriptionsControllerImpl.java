package oop.focus.finance.controller;

import javafx.scene.Parent;
import oop.focus.db.DataSource;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.finance.view.SubscriptionsView;

public class SubscriptionsControllerImpl implements SubscriptionsController {

    private final SubscriptionsView view;
    private final FinanceManager manager;

    public SubscriptionsControllerImpl(final DataSource db) {
        this.view = new SubscriptionsView(this);
        this.manager = new FinanceManagerImpl(db);
    }

    @Override
    public final Parent getView() {
        return this.view.getRoot();
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
