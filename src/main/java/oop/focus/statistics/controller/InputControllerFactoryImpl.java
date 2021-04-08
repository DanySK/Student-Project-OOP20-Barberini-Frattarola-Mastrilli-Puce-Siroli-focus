package oop.focus.statistics.controller;

import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.view.FinanceInputViewImpl;

public class InputControllerFactoryImpl implements InputControllerFactory {

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
                this.setView(new FinanceInputViewImpl(this, manager.getAccountManager().getAccounts()));
            }
        };
    }
}
