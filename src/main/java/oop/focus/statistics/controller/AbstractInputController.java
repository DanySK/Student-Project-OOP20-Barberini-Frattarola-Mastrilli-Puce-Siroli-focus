package oop.focus.statistics.controller;

import oop.focus.common.View;

/**
 * The Abstract input controller defines the common behavior of all inputs controller.
 * Input changes will be notified to a given {@link StatisticController}.
 *
 * @param <X> the type of the input
 */
public abstract class AbstractInputController<X> implements InputController<X> {

    private View view;
    private final StatisticController<X> controller;

    /**
     * Instantiates a new Abstract input controller and creates the associated view,
     * using the createView() method.
     *
     * @param controller the controller
     */
    public AbstractInputController(final StatisticController<X> controller) {
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
    protected final StatisticController<X> getController() {
        return this.controller;
    }

    /**
     * Create the view for the controller.
     * The view can be set using the setView() method.
     */
    protected abstract void createView();

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public abstract void updateInput(X input);
}
