package oop.focus.statistics.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Finance statistics controller manages the chart visualization, populating and updating
 * the views when the {@link TimePeriodInput} changes.
 */
public class FinanceStatisticsController implements UpdatableController<TimePeriodInput<Account>> {

    private final List<UpdatableController<TimePeriodInput<Account>>> charts;
    private final View content;
    private TimePeriodInput<Account> actualInput;

    /**
     * Instantiates a new Finance statistics controller and creates the associated view.
     *
     * @param manager the finance manager
     */
    public FinanceStatisticsController(final FinanceManager manager) {
        this.charts = new ArrayList<>();
        var factory = new FinanceChartFactoryImpl();
        this.charts.add(factory.periodExpenses(manager));
        this.charts.add(factory.balances(manager));
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
    public final void updateInput(final TimePeriodInput<Account> input) {
        if (!input.equals(this.actualInput)) {
            this.actualInput = input;
            this.charts.forEach(a -> a.updateInput(input));
        }
    }
}
