package oop.focus.application.controller;

import oop.focus.application.view.SectionsView;
import oop.focus.common.Controller;
import oop.focus.common.View;

public class SectionsController implements Controller, Update {
    private final SectionsView view;
    public SectionsController() {
        this.view = new SectionsView();
    }

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
    public final void update(final Controller controller) {
        this.view.update(controller);
    }
}
