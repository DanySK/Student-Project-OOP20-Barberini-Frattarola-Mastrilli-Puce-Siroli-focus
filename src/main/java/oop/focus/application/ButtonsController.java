package oop.focus.application;
import oop.focus.common.Controller;
import oop.focus.common.View;

public class ButtonsController implements Controller {
    private final UpperView upperView;
    public ButtonsController(final SectionsController controller) {
        this.upperView = new UpperView(controller);
    }

    @Override
    public final View getView() {
        return this.upperView;
    }
}
