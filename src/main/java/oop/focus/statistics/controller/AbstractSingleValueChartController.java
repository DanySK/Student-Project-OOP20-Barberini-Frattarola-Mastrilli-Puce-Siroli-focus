package oop.focus.statistics.controller;

import oop.focus.common.UpdatableController;
import oop.focus.common.View;
import oop.focus.statistics.view.PieChartView;
import oop.focus.statistics.view.SingleValueChart;

/**
 * The Abstract line chart controller defines a controller that manages a line chart.
 *
 * @param <X> the type of the input data to be displayed
 */
public abstract class AbstractSingleValueChartController<X> implements UpdatableController<X> {

    private final SingleValueChart chart;

    /**
     * Instantiates a new Abstract pie chart controller and creates the associated view.
     */
    public AbstractSingleValueChartController() {
        this.chart = new PieChartView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.chart;
    }

    /**
     * {@inheritDoc}
     */
    public abstract void updateInput(X input);

    /**
     * Gets the chart reference.
     *
     * @return the chart
     */
    protected final SingleValueChart getChart() {
        return this.chart;
    }
}
