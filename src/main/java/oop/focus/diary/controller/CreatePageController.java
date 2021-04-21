package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.WindowCreateNewPage;

/**
 * The controller of the window that manages the adding of a new page of diary.
 */
public class CreatePageController implements Controller {
    private final View content;

    /**
     * Instantiates a new create page controller.
     * @param controller    diary pages controller
     */
    public CreatePageController(final DiaryPagesController controller) {
        this.content = new WindowCreateNewPage(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.content;
    }
}
