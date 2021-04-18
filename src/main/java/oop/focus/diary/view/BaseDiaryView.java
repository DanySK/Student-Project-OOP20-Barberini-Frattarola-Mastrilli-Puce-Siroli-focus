package oop.focus.diary.view;

import javafx.scene.Node;
import oop.focus.common.View;
import oop.focus.diary.controller.DailyMoodController;
import oop.focus.diary.controller.DiaryPagesController;
import oop.focus.diary.controller.ToDoListController;

import java.util.List;

public class BaseDiaryView implements View {
    private final ToDoListController toDoListController;
    private final DiaryPagesController diaryController;
    private final DailyMoodController manager;
    public BaseDiaryView(final ToDoListController toDoListController, final DiaryPagesController diaryController, final DailyMoodController manager) {
        this.toDoListController = toDoListController;
        this.diaryController = diaryController;
        this.manager = manager;
    }

    @Override
    public Node getRoot() {
        return new CreateBoxFactoryImpl().createHBox(List.of(this.diaryController.getView().getRoot(),
                new CreateBoxFactoryImpl().createVBox(List.of(this.toDoListController.getView().getRoot(),
                this.manager.getView().getRoot())).getRoot())).getRoot();
    }
}
