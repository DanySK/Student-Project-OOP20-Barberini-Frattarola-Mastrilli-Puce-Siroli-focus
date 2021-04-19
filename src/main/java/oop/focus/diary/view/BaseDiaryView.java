package oop.focus.diary.view;

import javafx.scene.Node;
import oop.focus.common.View;
import oop.focus.diary.controller.DailyMoodController;
import oop.focus.diary.controller.DiaryPagesController;
import oop.focus.diary.controller.ToDoListController;

import java.util.List;

/**
 *  Implementation of {@link View}. The class represents the base schema of diary's section, composed by
 *  three under-sections : toDoList, DailyMood and the diary itself.
 */
public class BaseDiaryView implements View {
    private final ToDoListController toDoListController;
    private final DiaryPagesController diaryController;
    private final DailyMoodController manager;

    /**
     * Instantiates the three Controller relative to the three sections.
     * @param toDoListController    the Controller of to do list's section
     * @param diaryController   the Controller of diary's section
     * @param dailyMoodController   the Controller of daily mood's section
     */
    public BaseDiaryView(final ToDoListController toDoListController, final DiaryPagesController diaryController,
                         final DailyMoodController dailyMoodController) {
        this.toDoListController = toDoListController;
        this.diaryController = diaryController;
        this.manager = dailyMoodController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRoot() {
        return new ContainerFactoryImpl().mergeHorizontally(List.of(this.diaryController.getView().getRoot(),
                new ContainerFactoryImpl().mergeVertically(List.of(this.toDoListController.getView().getRoot(),
                this.manager.getView().getRoot())).getRoot())).getRoot();
    }
}
