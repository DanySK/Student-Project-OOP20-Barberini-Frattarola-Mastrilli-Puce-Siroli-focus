package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.AccountImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.view.windows.GenericWindow;
import oop.focus.finance.view.windows.NewAccountViewImpl;

/**
 * Immutable implementation of a new account controller.
 */
public class NewAccountControllerImpl implements NewAccountController {

    private final GenericWindow<NewAccountController> view;
    private final FinanceManager manager;

    public NewAccountControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new NewAccountViewImpl(this);
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
    public final void newAccount(final String name, final String color, final double amount) {
        this.manager.addAccount(new AccountImpl(name, color, (int) (amount * 100)));
    }
}
