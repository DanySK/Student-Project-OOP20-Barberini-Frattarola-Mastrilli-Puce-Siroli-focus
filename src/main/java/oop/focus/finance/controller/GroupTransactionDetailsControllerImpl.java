package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.windows.GenericWindow;
import oop.focus.finance.view.windows.GroupTransactionDetailsWindowImpl;

public class GroupTransactionDetailsControllerImpl implements DetailsController<GroupTransaction> {

    private final GenericWindow<DetailsController<GroupTransaction>> view;
    private final GroupTransaction transaction;
    private final FinanceManager manager;

    public GroupTransactionDetailsControllerImpl(final FinanceManager manager, final GroupTransaction transaction) {
        this.manager = manager;
        this.transaction = transaction;
        this.view = new GroupTransactionDetailsWindowImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final GroupTransaction getElement() {
        return this.transaction;
    }

    @Override
    public final void deleteElement(final GroupTransaction transaction) {
        this.manager.getGroupManager().removeTransaction(transaction);
    }
}
