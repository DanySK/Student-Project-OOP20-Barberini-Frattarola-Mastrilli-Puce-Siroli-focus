package oop.focus.diary.controller;

import oop.focus.application.controller.SectionsController;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;

import oop.focus.diary.view.abstractButtonsDiaryView;

/**
 * ButtonsDiaryController is the Controller of diary's lateral button.
 */
public class ButtonsDiaryController implements Controller {
    private final View content;

    /**
     * Instantiates a new buttons diary controller and creates the associated view.
     * @param controller    the sectionsController
     * @param dataSource    the{@link DataSource} from which to retrieve data
     */
    public ButtonsDiaryController(final SectionsController controller, final DataSource dataSource) {
        this.content = new abstractButtonsDiaryView(controller, dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.content;
    }
}
