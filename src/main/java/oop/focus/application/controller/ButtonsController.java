package oop.focus.application.controller;
import oop.focus.application.view.abstractButtonsAppView;
import oop.focus.common.Controller;
import oop.focus.common.UpdatableController;
import oop.focus.common.View;

/**
 * ButtonsController is the controller that manages the section's buttons.
 */
public class ButtonsController implements Controller {
    private final View buttonsView;
    public ButtonsController(final UpdatableController<Controller> controller) {
        this.buttonsView = new abstractButtonsAppView(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.buttonsView;
    }
}
