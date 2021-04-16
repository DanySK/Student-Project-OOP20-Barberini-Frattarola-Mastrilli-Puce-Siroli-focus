package oop.focus.application.controller;
import oop.focus.application.view.ButtonsView;
import oop.focus.common.Controller;
import oop.focus.common.View;

public class ButtonsController implements Controller {
    private final ButtonsView buttonsView;
    public ButtonsController(final SectionsController controller) {
        this.buttonsView = new ButtonsView(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.buttonsView;
    }
}
