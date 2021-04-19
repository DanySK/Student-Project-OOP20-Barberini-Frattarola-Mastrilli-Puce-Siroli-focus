package oop.focus.diary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.WindowRemoveAnnotation;

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
        final ObservableList<String> list = FXCollections.observableArrayList();
        controller.getObservableSet().forEach(s -> list.add(s.getName()));
        this.content = new WindowRemoveAnnotation<>(controller, list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.content;
    }
}
