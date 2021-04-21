package oop.focus.application.controller;
import oop.focus.common.Controller;
import oop.focus.common.UpdatableController;
import oop.focus.common.View;
import oop.focus.statistics.view.ViewFactoryImpl;
import java.util.List;

/**
 * GeneralController initializes a {@link SectionsController} and a {@link ButtonsController}
 * and combines their view.
 */
public class GeneralController implements Controller {
    private final View content;

    public GeneralController() {
        final UpdatableController<Controller> controller = new SectionsController();
        final Controller buttonController = new ButtonsController(controller);
        this.content = new ViewFactoryImpl()
                .createVertical(List.of(buttonController.getView().getRoot(), controller.getView().getRoot()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.content;
    }
}
