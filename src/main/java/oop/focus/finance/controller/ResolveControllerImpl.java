package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.windows.ResolveViewImpl;

import java.util.List;

public class ResolveControllerImpl implements ResolveController {

    private final ResolveViewImpl view;
    private final FinanceManager manager;

    public ResolveControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new ResolveViewImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void resolve() {
        this.manager.getGroupManager().resolve().forEach(this.manager.getGroupManager()::addTransaction);
    }

    @Override
    public final List<GroupTransaction> getResolvingTransactions() {
        return this.manager.getGroupManager().resolve();
    }
}
