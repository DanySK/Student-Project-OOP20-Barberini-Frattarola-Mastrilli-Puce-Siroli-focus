package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Label;
import oop.focus.common.View;
import oop.focus.diary.controller.ToDoListController;

public class RemoveTDLView implements View {
    private final View view;
    private final ObservableMap<Label, String> map;

    public RemoveTDLView(final ToDoListController controller) {
        this.map = FXCollections.observableHashMap();
        controller.allAnnotations().forEach(s -> this.map.put(new Label(s.getAnnotation()), s.getAnnotation()));
        this.view = new WindowRemoveAnnotation<>(controller, this.map);
    }
    @Override
    public final Node getRoot() {
        return this.view.getRoot();
    }
}
