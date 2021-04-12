package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.windows.GenericWindow;
import oop.focus.finance.view.windows.TransactionDetailsWindowImpl;

public class TransactionDetailsControllerImpl implements DetailsController<Transaction> {

    private final GenericWindow<DetailsController<Transaction>> view;
    private final Transaction transaction;
    private final FinanceManager manager;

    public TransactionDetailsControllerImpl(final FinanceManager manager, final Transaction subscription) {
        this.manager = manager;
        this.transaction = subscription;
        this.view = new TransactionDetailsWindowImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final Transaction getElement() {
        return this.transaction;
    }

    @Override
    public final void deleteElement(final Transaction transaction) {
        this.manager.removeTransaction(transaction);
    }
}
