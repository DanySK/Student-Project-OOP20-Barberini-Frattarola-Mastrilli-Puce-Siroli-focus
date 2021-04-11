package oop.focus.statistics.controller;

import oop.focus.common.View;
import oop.focus.statistics.view.LineChartView;
import oop.focus.statistics.view.MultiValueChart;
/**
 * The Abstract line chart controller defines a controller that manages a line chart.
 *
 * @param <X> the type of the input data to be displayed
 */
public abstract class AbstractLineChartController<X> implements ChartController<X> {

    private final MultiValueChart chart;

    /**
     * Instantiates a new Abstract line chart controller and creates the associated view.
     */
    public AbstractLineChartController() {
        this.chart = new LineChartView();
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
    protected final MultiValueChart getChart() {
        return this.chart;
    }
}
