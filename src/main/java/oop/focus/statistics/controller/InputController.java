package oop.focus.statistics.controller;

import oop.focus.common.Controller;

/**
 * The interface Input controller defines a controller that can handle input from any source.
 *
 * @param <X> the input type
 */
public interface InputController<X> extends Controller {
    /**
     * Update the input value.
     *
     * @param input the input
     */
    void updateInput(X input);
}
