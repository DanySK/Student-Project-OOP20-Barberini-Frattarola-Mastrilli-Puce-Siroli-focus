package oop.focus.diary.controller;
import oop.focus.application.controller.SectionsController;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.diary.view.CreateBoxFactoryImpl;

import java.util.List;

public class GeneralDiaryController implements Controller {
    private final View content;

    public GeneralDiaryController(final DataSource dataSource) {
        final SectionsController controller = new SectionsController();
        final ButtonsDiaryController buttonController = new ButtonsDiaryController(controller, dataSource);
        this.content = new CreateBoxFactoryImpl().createHBox(List.of(buttonController.getView().getRoot(), controller.getView().getRoot()));
    }

    @Override
    public final View getView() {
        return this.content;
    }
}
