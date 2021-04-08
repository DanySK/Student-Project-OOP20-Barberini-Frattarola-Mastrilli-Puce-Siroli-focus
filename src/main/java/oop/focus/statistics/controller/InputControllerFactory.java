package oop.focus.statistics.controller;

import oop.focus.finance.model.FinanceManager;

/**
 * The interface Input controller factory defines methods to create different types of input controllers.
 */
public interface InputControllerFactory {
    /**
     * Creates an input controller that uses {@link FinanceInput} as input type.
     *
     * @param controller the controller to notify when the input changes.
     * @param manager    the finance manager to retrieve data.
     * @return the input controller
     */
    InputController<FinanceInput> financeInputController(StatisticController<FinanceInput> controller,
                                                         FinanceManager manager);
}
