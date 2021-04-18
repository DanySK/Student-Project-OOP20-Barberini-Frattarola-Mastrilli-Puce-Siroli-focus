package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.windows.ResolveViewImpl;

import java.util.List;

/**
 * Immutable implementation of a resolve credit controller.
 */
public class ResolveControllerImpl implements ResolveController {

    private final ResolveViewImpl view;
    private final FinanceManager manager;

    private final ObservableSet<GroupTransaction> groupTransactions;

    public ResolveControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new ResolveViewImpl(this);
        this.groupTransactions = this.manager.getGroupManager().getTransactions();
        this.addListeners();
    }

    private void addListeners() {
        this.groupTransactions.addListener((SetChangeListener<GroupTransaction>) change -> this.view.populate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void resolve() {
        this.manager.getGroupManager().resolveList().forEach(this.manager.getGroupManager()::addTransaction);
    }

    @Override
    public final List<GroupTransaction> getResolvingTransactions() {
        return this.manager.getGroupManager().resolveList();
    }
}
