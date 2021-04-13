package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import oop.focus.common.View;
import oop.focus.diary.controller.SingleCheckBoxController;
import oop.focus.diary.controller.ToDoListController;

public class RemoveTDLView implements View {
    private final View view;
    private final ObservableMap<CheckBox, String> map;
    private final SingleCheckBoxController checkBoxController;
    public RemoveTDLView(final ToDoListController controller) {
        this.map = FXCollections.observableHashMap();
        this.checkBoxController = new SingleCheckBoxController();
        controller.allAnnotations().forEach(s -> this.map.put(this.checkBoxController.createCheckBox(s), s.getAnnotation()));
        this.view = new WindowRemoveAnnotation<>(controller, this.map);
    }
    @Override
    public final Node getRoot() {
        return this.view.getRoot();
    }
}
