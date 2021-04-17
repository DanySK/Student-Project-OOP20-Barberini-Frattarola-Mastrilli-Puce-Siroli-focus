package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import oop.focus.common.View;
import oop.focus.diary.controller.ToDoListController;

public class RemoveTDLView implements View {
    private final View view;

    public RemoveTDLView(final ToDoListController controller) {
        final ObservableList<String> list = FXCollections.observableArrayList();
        controller.allAnnotations().forEach(s -> list.add(s.getAnnotation()));
        this.view = new WindowRemoveAnnotation<>(controller, list);
    }
    @Override
    public final Node getRoot() {
        return this.view.getRoot();
    }
}
