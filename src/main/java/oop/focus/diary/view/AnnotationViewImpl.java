package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import oop.focus.common.View;
import oop.focus.diary.controller.SingleCheckBoxController;
import oop.focus.diary.controller.ToDoListController;
import oop.focus.diary.model.ToDoAction;


public class AnnotationViewImpl implements View {
    //private static final double CHECKBOX_HEIGHT =  0.05;
    private final ListView<CheckBox> listView;
    private final ObservableList<CheckBox> checkBoxes;
    private final ToDoListController controller;
    private final SingleCheckBoxController checkBoxController;
    public AnnotationViewImpl(final ToDoListController controller)  {
        this.controller = controller;
        this.checkBoxController = new SingleCheckBoxController();
        this.checkBoxes =  FXCollections.observableArrayList();
        this.listView = new ListView<>();
        this.updateTDLView();
        controller.allAnnotations().addListener((SetChangeListener<ToDoAction>) c -> {
            if (c.wasAdded()) {
                final ToDoAction change = c.getElementAdded();
                this.checkBoxes.add(this.checkBoxController.createCheckBox(change));
            } else if (c.wasRemoved()) {
                this.listView.getItems().clear();
                this.updateTDLView();
            }
        });
        this.checkBoxes.forEach(a -> a.setOnAction(event -> controller.changeCheck(a.getText())));
        this.listView.setItems(this.checkBoxes);
    }
    private void updateTDLView() {
        controller.allAnnotations().stream().map(this.checkBoxController::createCheckBox).forEach(this.checkBoxes::add);
    }
    @Override
    public final Node getRoot() {
        return this.listView;
    }
}
