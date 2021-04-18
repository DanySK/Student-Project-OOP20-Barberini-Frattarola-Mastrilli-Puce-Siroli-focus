package oop.focus.statistics.controller;

import oop.focus.common.UpdatableController;
import oop.focus.db.DataSource;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;

/**
 * The interface Input controller factory defines methods to create different types of input controllers.
 */
public interface InputControllerFactory {
    /**
     * Creates an input controller that uses a {@link TimePeriodInput} of Accounts as input type.
     *
     * @param controller the controller to notify when the input changes.
     * @param manager    the finance manager to retrieve data.
     * @return the input controller
     */
    UpdatableController<TimePeriodInput<Account>> financeInputController(
            UpdatableController<TimePeriodInput<Account>> controller,
            FinanceManager manager);

    /**
     * Creates an input controller that uses {@link TimePeriodInput} of events as input type.
     *
     * @param controller the controller to notify when the input changes.
     * @param dataSource the data source to retrieve data.
     * @return the abstract input controller
     */
    UpdatableController<TimePeriodInput<String>> eventsInputController(
            UpdatableController<TimePeriodInput<String>> controller,
            DataSource dataSource);

}
