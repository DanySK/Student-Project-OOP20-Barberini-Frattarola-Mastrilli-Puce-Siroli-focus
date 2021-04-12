package oop.focus.application;
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
                .createVerticalAutoResizing(List.of(buttonController.getView(), controller.getView()));
    }

    @Override
    public final View getView() {
        return this.content;
    }
}
