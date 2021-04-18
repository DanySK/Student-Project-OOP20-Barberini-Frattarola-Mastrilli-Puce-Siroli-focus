package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.RemovePageView;

/**
 * Implementation of {@link Controller}. This class manages the elimination of diary's pages.
 */
public class RemovePageController implements Controller {
    private final View content;

    /**
     * Instantiates a new remove page controller and creates the associated view.
     * @param controller    the diaryPagesController
     */
    public RemovePageController(final DiaryPagesController controller) {
        this.content = new RemovePageView(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.content;
    }
}
