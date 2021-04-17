package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import oop.focus.common.View;
import oop.focus.diary.controller.DiaryPages;

public class RemovePageView implements View {
    private final View view;
    public RemovePageView(final DiaryPages controller) {
        final ObservableList<String> list = FXCollections.observableArrayList();
        controller.getObservableSet().forEach(s -> list.add(s.getName()));
        this.view = new WindowRemoveAnnotation<>(controller, list);
    }
    @Override
    public final Node getRoot() {
        return this.view.getRoot();
    }
}
