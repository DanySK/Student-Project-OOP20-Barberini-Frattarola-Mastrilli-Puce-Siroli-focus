package oop.focus.diary.view;

import oop.focus.common.View;
/**
 * The interface updatable view defines a View to which an input change can be notified.
 *
 * @param <X> the type of the input.
 */
public interface UpdatableView<X> extends View {
    /**
     * Update the input.
     *
     * @param input the input
     */
    void updateInput(X input);

}
