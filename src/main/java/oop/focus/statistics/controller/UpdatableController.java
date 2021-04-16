package oop.focus.statistics.controller;

import oop.focus.common.Controller;

/**
 * The interface Updatable controller defines a controller to which an input change can be notified.
 *
 * @param <X> the type of the input.
 */
public interface UpdatableController<X> extends Controller {
    /**
     * Update the input.
     *
     * @param input the input
     */
    void updateInput(X input);
}
