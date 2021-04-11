package oop.focus.statistics.controller;

import oop.focus.db.DataSource;
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
    public final AbstractInputController<FinanceInput> financeInputController(final StatisticController<FinanceInput> controller,
                                                                              final FinanceManager manager) {
        return new AbstractInputController<>(controller) {
            @Override
            public void updateInput(final FinanceInput input) {
                super.getController().updateInput(input);
            }

            @Override
            protected void createView() {
                this.setView(new InputViewFactoryImpl()
                        .financeInputView(manager.getAccountManager().getAccounts(), this));
            }
        };
    }

    @Override
    public final AbstractInputController<EventsInput> eventsInputController(final StatisticController<EventsInput> controller,
                                                                            final DataSource dataSource) {
        return new AbstractInputController<>(controller) {
            @Override
            public void updateInput(final EventsInput input) {
                super.getController().updateInput(input);
            }

            @Override
            protected void createView() {
                this.setView(new InputViewFactoryImpl()
                        .eventsInputView(dataSource.getEvents().getAll(), this));
            }
        };
    }
}
