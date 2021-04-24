package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.view.bases.BaseView;
import oop.focus.finance.view.bases.BaseViewImpl;

/**
 * Immutable implementation of a change view controller.
 */
public class ChangeViewControllerImpl implements ChangeViewController {

    private final BaseView view;
    private final FinanceManager manager;

    public ChangeViewControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new BaseViewImpl(this);
        this.changeView(new TransactionsControllerImpl(manager, t -> true).getView());
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
    public final void changeView(final View view) {
        this.view.changeView(view);
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }
}
