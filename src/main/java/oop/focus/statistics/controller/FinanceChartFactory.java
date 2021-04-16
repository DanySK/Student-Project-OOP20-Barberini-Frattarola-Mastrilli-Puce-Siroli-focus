package oop.focus.statistics.controller;

import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;

/**
 * The interface Finance single value chart factory defines methods to create
 * different {@link UpdatableController} about finance.
 */
public interface FinanceChartFactory {
    /**
     * Creates a {@link UpdatableController} that manage a chart
     * that shows the balances for each different {@link Account}.
     *
     * @param manager the {@link FinanceManager} from which to retrieve data
     * @return the chart controller
     */
    UpdatableController<TimePeriodInput<Account>> balances(FinanceManager manager);

    /**
     * Creates a {@link UpdatableController} that manage a chart
     * that shows the daily expenses for each
     * different {@link Account}.
     *
     * @param manager the {@link FinanceManager} from which to retrieve data
     * @return the chart controller
     */
    UpdatableController<TimePeriodInput<Account>> periodExpenses(FinanceManager manager);
}
