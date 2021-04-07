package oop.focus.statistics.controller;

import oop.focus.common.Controller;

/**
 * The interface Statistic controller defines a controller for a generic statistic section.
 *
 * @param <X> the type of the input used to update the data.
 */
public interface StatisticController<X> extends Controller {
    /**
     * Update the dataset used to create the statistics using the given input.
     *
     * @param input the input
     */
    void updateInput(X input);
}
