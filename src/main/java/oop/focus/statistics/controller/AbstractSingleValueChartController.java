package oop.focus.statistics.controller;

import oop.focus.common.UpdatableController;
import oop.focus.common.View;
import oop.focus.statistics.view.PieChartView;
import oop.focus.statistics.view.SingleValueChart;

import java.util.Objects;

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
        this.chart = this.createView();
    }

    /**
     * This method creates and assigns the dedicated chart view to this controller.
     * The default implementation uses a {@link PieChartView}
     * Overriding this method will change the view of the chart.
     * This method is called by the constructor and should not be called elsewhere.
     *
     * @return the SingleValueChart
     */
    protected SingleValueChart createView() {
        return Objects.requireNonNullElseGet(this.chart, PieChartView::new);
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
