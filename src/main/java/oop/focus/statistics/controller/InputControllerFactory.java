package oop.focus.statistics.controller;

import oop.focus.db.DataSource;
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

    /**
     * Creates an input controller that uses {@link EventsInput} as input type.
     *
     * @param controller the controller to notify when the input changes.
     * @param dataSource the data source to retrieve data.
     * @return the abstract input controller
     */
    InputController<EventsInput> eventsInputController(StatisticController<EventsInput> controller,
                                                       DataSource dataSource);

}
