package oop.focus.statistics.controller;

import oop.focus.common.UpdatableController;
import oop.focus.common.View;

/**
 * The Abstract input controller defines the common behavior of all inputs controller.
 * Input changes will be notified to a given {@link UpdatableController}.
 *
 * @param <X> the type of the input
 */
public abstract class AbstractInputController<X> implements UpdatableController<X> {

    private View view;
    private final UpdatableController<X> controller;

    /**
     * Instantiates a new Abstract input controller and creates the associated view,
     * using the createView() method.
     *
     * @param controller the controller to which the input will be updated
     */
    public AbstractInputController(final UpdatableController<X> controller) {
        this.controller = controller;
        this.createView();
    }

    /**
     * Sets the view of the controller.
     *
     * @param view the view
     */
    protected final void setView(final View view) {
        this.view = view;
    }

    /**
     * Gets the controller reference.
     *
     * @return the controller
     */
    protected final UpdatableController<X> getController() {
        return this.controller;
    }

    /**
     * Create the view for the controller.
     * The view can be set using the setView() method.
     */
    protected abstract void createView();

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final X input) {
        this.controller.updateInput(input);
    }
}
