package oop.focus.statistics.controller;

import oop.focus.finance.model.FinanceManager;

/**
 * The interface Finance multi value chart factory defines methods to create
 * different {@link ChartController} of a {@link oop.focus.statistics.view.MultiValueChart}.
 */
public interface FinanceMultiValueChartFactory {
    /**
     * Creates a {@link ChartController} that manage a chart
     * that shows the daily expenses for each
     * different {@link oop.focus.finance.model.Account}.
     *
     * @param manager the {@link FinanceManager} from which to retrieve data
     * @return the chart controller
     */
    ChartController<FinanceInput> periodExpenses(FinanceManager manager);
}
