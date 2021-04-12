package oop.focus.application;

import oop.focus.common.Controller;
import oop.focus.common.View;

public class SectionsController implements Controller {
    private final SectionsView view;
    public SectionsController() {
        this.view = new SectionsView();
    }
    public final void setPane(final Controller controller) {
        this.view.setPane(controller);
    }
    @Override
    public final View getView() {
        return this.view;
    }
}
