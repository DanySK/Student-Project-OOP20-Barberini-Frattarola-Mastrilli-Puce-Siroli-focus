package oop.focus.application.controller;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.statistics.view.ViewFactoryImpl;
import java.util.List;

public class GeneralController implements Controller {
    private final View content;

    public GeneralController() {
        final SectionsController controller = new SectionsController();
        final ButtonsController buttonController = new ButtonsController(controller);
        this.content = new ViewFactoryImpl()
                .createVertical(List.of(buttonController.getView().getRoot(), controller.getView().getRoot()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.content;
    }
}
