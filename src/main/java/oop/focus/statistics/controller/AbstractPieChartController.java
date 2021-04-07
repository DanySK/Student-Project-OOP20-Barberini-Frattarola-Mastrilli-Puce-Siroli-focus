package oop.focus.statistics.controller;

import oop.focus.common.View;
import oop.focus.statistics.view.PieChartView;

/**
 * The Abstract line chart controller defines a controller that manages a line chart.
 *
 * @param <X> the type of the input data to be displayed
 */
public abstract class AbstractPieChartController<X> implements ChartController<X> {
    private final PieChartView chart;

    /**
     * Instantiates a new Abstract pie chart controller and creates the associated view.
     */
    public AbstractPieChartController() {
        this.chart = new PieChartView();
    }

    @Override
    public final View getView() {
        return this.chart;
    }

    public abstract void updateInput(X input);

    /**
     * Gets the chart reference.
     *
     * @return the chart
     */
    protected final PieChartView getChart() {
        return this.chart;
    }
}
