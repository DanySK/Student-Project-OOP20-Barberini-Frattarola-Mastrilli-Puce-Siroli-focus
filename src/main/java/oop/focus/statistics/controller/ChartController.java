package oop.focus.statistics.controller;

import oop.focus.common.Controller;

/**
 * The interface Chart controller defines a controller for a chart section.
 *
 * @param <X> the type of the input used to change the chart data.
 */
public interface ChartController<X> extends Controller {
    /**
     * Update the input.
     *
     * @param input the input
     */
    void updateInput(X input);
}
