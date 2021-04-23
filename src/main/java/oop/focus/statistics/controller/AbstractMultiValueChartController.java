package oop.focus.statistics.controller;

import oop.focus.common.UpdatableController;
import oop.focus.common.View;
import oop.focus.statistics.view.LineChartView;
import oop.focus.statistics.view.MultiValueChart;

import java.util.Objects;

/**
 * The Abstract line chart controller defines a controller that manages a line chart.
 *
 * @param <X> the type of the input data to be displayed
 */
public abstract class AbstractMultiValueChartController<X> implements UpdatableController<X> {

    private final MultiValueChart chart;

    /**
     * Instantiates a new Abstract line chart controller and creates the associated view.
     */
    public AbstractMultiValueChartController() {
        this.chart = this.getChartView();
    }

    /**
     * This method creates and assigns the dedicated chart view to this controller.
     * The default implementation uses a {@link LineChartView}
     * Overriding this method will change the view of the chart.
     * This method is called by the constructor and should not be called elsewhere.
     *
     * @return the MultiValueChart
     */
    protected MultiValueChart getChartView() {
        return Objects.requireNonNullElseGet(this.chart, LineChartView::new);
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
