package oop.focus.statistics.controller;

import oop.focus.common.UpdatableController;
import oop.focus.db.DataSource;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.view.InputViewFactoryImpl;

/**
 * Implementation of {@link InputControllerFactory}.
 */
public class InputControllerFactoryImpl implements InputControllerFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final AbstractInputController<TimePeriodInput<Account>> financeInputController(
            final UpdatableController<TimePeriodInput<Account>> controller,
            final FinanceManager manager) {
        return new AbstractInputController<>(controller) {
            @Override
            protected void createView() {
                this.setView(new InputViewFactoryImpl()
                        .financeInputView(manager.getAccountManager().getElements(), this));
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final AbstractInputController<TimePeriodInput<String>> eventsInputController(
            final UpdatableController<TimePeriodInput<String>> controller,
            final DataSource dataSource) {
        return new AbstractInputController<>(controller) {
            @Override
            protected void createView() {
                this.setView(new InputViewFactoryImpl()
                        .eventsInputView(dataSource.getEvents().getAll(), this));
            }
        };
    }
}
