package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.windows.GenericWindow;
import oop.focus.finance.view.windows.SubscriptionDetailsWindowImpl;

public class SubscriptionDetailsControllerImpl implements DetailsController<Transaction> {

    private final GenericWindow<DetailsController<Transaction>> view;
    private final Transaction subscription;
    private final FinanceManager manager;

    public SubscriptionDetailsControllerImpl(final FinanceManager manager, final Transaction subscription) {
        this.manager = manager;
        this.subscription = subscription;
        this.view = new SubscriptionDetailsWindowImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final Transaction getElement() {
        return this.subscription;
    }

    @Override
    public final void deleteElement(final Transaction subscription) {
        this.manager.getTransactionManager().stopRepeat(subscription);
    }
}
