package oop.focus.statistics.controller;

import oop.focus.finance.model.FinanceManager;

/**
 * The interface Finance single value chart factory defines methods to create
 * different {@link ChartController} of a {@link oop.focus.statistics.view.SingleValueChart}.
 */
public interface FinanceSingleValueChartFactory {
    /**
     * Creates a {@link ChartController} that manage a chart
     * that shows the balances for each different {@link oop.focus.finance.model.Account}.
     *
     * @param manager the {@link FinanceManager} from which to retrieve data
     * @return the chart controller
     */
    ChartController<FinanceInput> balances(FinanceManager manager);
}
