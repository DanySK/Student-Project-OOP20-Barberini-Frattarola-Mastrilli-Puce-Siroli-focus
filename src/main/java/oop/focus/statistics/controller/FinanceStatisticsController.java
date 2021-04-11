package oop.focus.statistics.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Finance statistics controller manages the chart visualization, populating and updating
 * the views when the {@link FinanceInput} changes.
 */
public class FinanceStatisticsController implements StatisticController<FinanceInput> {

    private final List<ChartController<FinanceInput>> charts;
    private final View content;
    /**
     * Instantiates a new Finance statistics controller and creates the associated view.
     *
     * @param manager the finance manager
     */
    public FinanceStatisticsController(final FinanceManager manager) {
        this.charts = new ArrayList<>();
        var pieFactory = new FinanceSingleValueChartFactoryImpl();
        var lineFactory = new FinanceMultiValueChartFactoryImpl();
        this.charts.add(lineFactory.periodExpenses(manager));
        this.charts.add(pieFactory.balances(manager));
        this.content = new ViewFactoryImpl().createVerticalAutoResizing(this.charts.stream()
                .map(Controller::getView).collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateInput(final FinanceInput input) {
        this.charts.forEach(a -> a.updateInput(input));
    }
}
