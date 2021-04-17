package oop.focus.diary.controller;

import oop.focus.common.Controller;
/**
 * The interface Remove controller defines a controller that deletes the input.
 *
 * @param <X> the type of the input.
 */
public interface RemoveControllers<X> extends Controller {
    /**
     * Update the input.
     *
     * @param input the input
     */
    void remove(X input);
}
